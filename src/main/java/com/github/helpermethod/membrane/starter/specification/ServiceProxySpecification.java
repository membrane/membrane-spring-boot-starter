package com.github.helpermethod.membrane.starter.specification;

import com.predic8.membrane.core.rules.ServiceProxy;

import java.util.function.Consumer;

public class ServiceProxySpecification {
    private final ServiceProxy serviceProxy;

    public ServiceProxySpecification(ServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public ServiceProxySpecification matches(Consumer<MatcherSpecification> c) {
        return this;
    }

    public ServiceProxySpecification interceptors(Consumer<InterceptorsSpecification> c) {
        return this;
    }

    public ServiceProxySpecification target(Consumer<TargetSpecification> c) {
        return this;
    }

}