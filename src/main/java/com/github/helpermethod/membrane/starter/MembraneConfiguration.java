package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import com.github.helpermethod.membrane.starter.servlet.ServletTransport;
import com.github.helpermethod.membrane.starter.specification.ProxiesSpecification;
import com.predic8.membrane.core.HttpRouter;
import com.predic8.membrane.core.Router;
import com.predic8.membrane.core.interceptor.DispatchingInterceptor;
import com.predic8.membrane.core.interceptor.HTTPClientInterceptor;
import com.predic8.membrane.core.interceptor.RuleMatchingInterceptor;
import com.predic8.membrane.core.interceptor.UserFeatureInterceptor;
import com.predic8.membrane.core.interceptor.rewrite.ReverseProxyingInterceptor;
import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.transport.Transport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.io.IOException;
import java.util.*;

@Configuration
public class MembraneConfiguration {
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
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();

        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("/api", "membraneController");
        simpleUrlHandlerMapping.setUrlMap(urlMap);

        return simpleUrlHandlerMapping;
    }

    @DependsOn("router")
    @Bean
    public MembraneController membraneController() {
        return new MembraneController();
    }
}