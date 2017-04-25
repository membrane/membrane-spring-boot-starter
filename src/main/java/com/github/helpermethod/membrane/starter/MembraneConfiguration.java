package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import com.github.helpermethod.membrane.starter.servlet.ServletTransport;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class MembraneConfiguration {
    @Bean
    public Transport transport() {
        ServletTransport servletTransport = new ServletTransport();

        Collections.addAll(
            servletTransport.getInterceptors(),
            new ReverseProxyingInterceptor(),
            new RuleMatchingInterceptor(),
            new DispatchingInterceptor(),
            new UserFeatureInterceptor(),
            new HTTPClientInterceptor());

        return servletTransport;
    }

    @ConditionalOnMissingBean(ProxiesConfiguration.class)
    @Bean
    public ProxiesConfiguration proxies() {
        return p -> {
        };
    }

    @Bean
    public Router router(Transport transport, ProxiesConfiguration proxiesConfiguration) throws IOException {
        List<ServiceProxy> serviceProxies = new ArrayList<>();
        proxiesConfiguration.consume(new ProxiesSpecification(serviceProxies));

        HttpRouter httpRouter = new HttpRouter();
        httpRouter.setTransport(transport);

        for (ServiceProxy serviceProxy : serviceProxies) {
            httpRouter.add(serviceProxy);
        }

        httpRouter.setHotDeploy(false);
        httpRouter.start();

        return httpRouter;
    }

    @Bean
    public MembraneController membraneController() {
        return new MembraneController();
    }
}