package com.github.helpermethod.membrane.starter.specification;

import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.rules.ServiceProxyKey;

import java.util.function.Consumer;

public class ServiceProxySpecification {
    private final ServiceProxy serviceProxy;

    public ServiceProxySpecification(ServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public ServiceProxySpecification matches(Consumer<MatcherSpecification> c) {
        c.accept(new MatcherSpecification(serviceProxy));

        return this;
    }

    public ServiceProxySpecification interceptors(Consumer<InterceptorsSpecification> c) {
        c.accept(new InterceptorsSpecification(serviceProxy.getInterceptors()));

        return this;
    }

    public ServiceProxySpecification target(Consumer<TargetSpecification> c) {
        c.accept(new TargetSpecification(serviceProxy.getTarget()));

        return this;
    }
}