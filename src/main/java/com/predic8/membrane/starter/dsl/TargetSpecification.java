package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.rules.AbstractServiceProxy.Target;

public class TargetSpecification {
    private final Target target;

    public TargetSpecification(Target target) {
        this.target = target;
    }

    public TargetSpecification host(String host) {
        target.setHost(host);

        return this;
    }

    public TargetSpecification port(int port) {
        target.setPort(port);

        return this;
    }

    public TargetSpecification url(String url) {
        target.setUrl(url);

        return this;
    }
}
