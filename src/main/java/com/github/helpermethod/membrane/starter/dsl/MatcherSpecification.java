package com.github.helpermethod.membrane.starter.dsl;

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

    public MatcherSpecification pathPrefix(String path) {
        serviceProxy.setPath(new Path(false, path));

        return this;
    }

    public MatcherSpecification pathRegex(String pathRegex) {
        serviceProxy.setPath(new Path(true, pathRegex));

        return this;
    }
}