package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import com.github.helpermethod.membrane.starter.mapping.PrefixHandlerMapping;
import com.github.helpermethod.membrane.starter.servlet.ServletTransport;
import com.github.helpermethod.membrane.starter.specification.ProxiesSpecification;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

@Configuration
public class MembraneConfiguration {
    @ConditionalOnMissingBean(Transport.class)
    @Bean
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

    @ConditionalOnMissingBean(ProxiesConfiguration.class)
    @Bean
    public ProxiesConfiguration proxies() {
        return p -> {
        };
    }

    @Bean
    public Router router(Transport transport, ProxiesConfiguration proxiesConfiguration) throws IOException {
        Router router = new Router();
        router.setTransport(transport);

        List<ServiceProxy> serviceProxies = new ArrayList<>();
        proxiesConfiguration.consume(new ProxiesSpecification(serviceProxies));

        for (ServiceProxy serviceProxy : serviceProxies) {
            router.add(serviceProxy);
        }

        router.setHotDeploy(false);
        router.start();

        return router;
    }

    @Bean
    public PathLocator pathLocator(Router router) {
        Map<Boolean, List<RuleKey>> ruleKeyByType = router.getRuleManager().getRules().stream()
                                                          .map(Rule::getKey)
                                                          .collect(partitioningBy(RuleKey::isPathRegExp));

        return new PathLocator(
            ruleKeyByType.get(false).stream().map(RuleKey::getPath).collect(toList()),
            ruleKeyByType.get(true).stream().map(RuleKey::getPath).collect(toList())
        );
    }

    @Bean
    public AbstractUrlHandlerMapping membraneHandlerMapping(PathLocator pathLocator, MembraneController membraneController) {
        AbstractUrlHandlerMapping membraneHandlerMapping = new PrefixHandlerMapping(pathLocator.prefixPaths(), membraneController);
        membraneHandlerMapping.setOrder(-200);

        return membraneHandlerMapping;
    }

    @Bean
    public MembraneController membraneController() {
        return new MembraneController();
    }
}