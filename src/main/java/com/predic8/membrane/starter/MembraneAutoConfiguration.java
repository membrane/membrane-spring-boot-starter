package com.predic8.membrane.starter;

import com.predic8.membrane.core.Router;
import com.predic8.membrane.core.interceptor.DispatchingInterceptor;
import com.predic8.membrane.core.interceptor.HTTPClientInterceptor;
import com.predic8.membrane.core.interceptor.RuleMatchingInterceptor;
import com.predic8.membrane.core.interceptor.UserFeatureInterceptor;
import com.predic8.membrane.core.interceptor.rewrite.ReverseProxyingInterceptor;
import com.predic8.membrane.core.rules.Rule;
import com.predic8.membrane.core.rules.RuleKey;
import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.transport.Transport;
import com.predic8.membrane.starter.MembraneMarkerConfiguration.Marker;
import com.predic8.membrane.starter.controller.MembraneController;
import com.predic8.membrane.starter.dsl.ProxiesSpecification;
import com.predic8.membrane.starter.mapping.PathHandlerMapping;
import com.predic8.membrane.starter.servlet.ServletTransport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

@Configuration
@ConditionalOnBean(Marker.class)
public class MembraneAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public Transport transport() {
        Transport transport = new ServletTransport();

        Collections.addAll(
            transport.getInterceptors(),
            new ReverseProxyingInterceptor(),
            new RuleMatchingInterceptor(),
            new DispatchingInterceptor(),
            new UserFeatureInterceptor(),
            new HTTPClientInterceptor());

        return transport;
    }

    @Bean
    @ConditionalOnMissingBean
    public Proxies proxies() {
        return p -> {
        };
    }

    @Bean
    public Router router(Transport transport, Proxies proxies) throws IOException {
        Router router = new Router();
        router.setTransport(transport);

        List<ServiceProxy> serviceProxies = new ArrayList<>();
        proxies.consume(new ProxiesSpecification(serviceProxies));

        for (ServiceProxy serviceProxy : serviceProxies) {
            router.add(serviceProxy);
        }

        router.setHotDeploy(false);
        router.start();

        return router;
    }

    @Bean
    public PathResolver pathLocator(Router router) {
        Map<Boolean, List<RuleKey>> ruleKeyByType = router.getRuleManager()
                                                          .getRules()
                                                          .stream()
                                                          .map(Rule::getKey)
                                                          .collect(partitioningBy(RuleKey::isPathRegExp));

        return new PathResolver(
            ruleKeyByType.get(false).stream().map(RuleKey::getPath).collect(toList()),
            ruleKeyByType.get(true).stream().map(RuleKey::getPath).collect(toList())
        );
    }

    @Bean
    public AbstractUrlHandlerMapping membranePrefixHandlerMapping(PathResolver pathResolver, MembraneController membraneController) {
        PathHandlerMapping membranePrefixHandlerMapping = new PathHandlerMapping(pathResolver.prefixPaths(), String::startsWith, membraneController);
        membranePrefixHandlerMapping.setOrder(-200);

        return membranePrefixHandlerMapping;
    }

    @Bean
    public AbstractUrlHandlerMapping membraneRegexHandlerMapping(PathResolver pathResolver, MembraneController membraneController) {
        PathHandlerMapping membraneRegexHandlerMapping = new PathHandlerMapping(pathResolver.regexPaths(), String::matches, membraneController);
        membraneRegexHandlerMapping.setOrder(-200);

        return membraneRegexHandlerMapping;
    }

    @Bean
    public MembraneController membraneController() {
        return new MembraneController();
    }
}