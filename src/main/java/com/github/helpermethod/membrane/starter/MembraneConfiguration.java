package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import com.github.helpermethod.membrane.starter.controller.MembraneHandlerMapping;
import com.github.helpermethod.membrane.starter.servlet.ServletTransport;
import com.github.helpermethod.membrane.starter.specification.ProxiesSpecification;
import com.predic8.membrane.core.HttpRouter;
import com.predic8.membrane.core.Router;
import com.predic8.membrane.core.interceptor.DispatchingInterceptor;
import com.predic8.membrane.core.interceptor.HTTPClientInterceptor;
import com.predic8.membrane.core.interceptor.RuleMatchingInterceptor;
import com.predic8.membrane.core.interceptor.UserFeatureInterceptor;
import com.predic8.membrane.core.interceptor.rewrite.ReverseProxyingInterceptor;
import com.predic8.membrane.core.rules.RuleKey;
import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.transport.Transport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public AbstractUrlHandlerMapping membraneHandlerMapping(Router router, MembraneController membraneController) {
        Map<Boolean, List<RuleKey>> ruleKeyByType = router.getRuleManager().getRules().stream()
                                                          .map(r -> r.getKey())
                                                          .collect(Collectors.groupingBy(RuleKey::isPathRegExp));
        List<String> prefixPaths = ruleKeyByType.get(false).stream().map(r -> r.getPath()).collect(toList());
        List<String> regexPaths = ruleKeyByType.get(true).stream().map(r -> r.getPath()).collect(toList());

        AbstractUrlHandlerMapping membraneHandlerMapping = new MembraneHandlerMapping(prefixPaths, regexPaths, membraneController);
        membraneHandlerMapping.setOrder(-200);

        return membraneHandlerMapping;
    }

    @Bean
    public MembraneController membraneController() {
        return new MembraneController();
    }
}