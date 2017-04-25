package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.specification.ServiceProxySpecification;
import com.predic8.membrane.core.rules.ServiceProxy;

import java.util.List;
import java.util.function.Consumer;

public class ProxiesSpecification {
    private final List<ServiceProxy> serviceProxies;

    public ProxiesSpecification(List<ServiceProxy> serviceProxies) {
        this.serviceProxies = serviceProxies;
    }

    public ProxiesSpecification serviceProxy(Consumer<ServiceProxySpecification> c) {
        ServiceProxy serviceProxy = new ServiceProxy();
        c.accept(new ServiceProxySpecification(serviceProxy));

        serviceProxies.add(serviceProxy);

        return this;
    }
}