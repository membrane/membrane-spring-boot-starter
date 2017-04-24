package com.github.helpermethod.membrane.starter.specification;

import com.predic8.membrane.core.config.Path;
import com.predic8.membrane.core.rules.ServiceProxy;

public class MatcherSpecification {
    private final ServiceProxy serviceProxy;

    public MatcherSpecification(ServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public MatcherSpecification method(String method) {
        serviceProxy.setMethod(method);

        return this;
    }

    public MatcherSpecification path(String path) {
        serviceProxy.setPath(new Path(false, path));

        return this;
    }

    public MatcherSpecification pathRegex(String pathRegex) {
        serviceProxy.setPath(new Path(false, pathRegex));

        return this;
    }
}