package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.config.Path;
import com.predic8.membrane.core.rules.ServiceProxy;
import org.springframework.http.HttpMethod;

public class MatcherSpecification {
    private final ServiceProxy serviceProxy;

    public MatcherSpecification(ServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public MatcherSpecification method(String method) {
        serviceProxy.setMethod(method);

        return this;
    }

    public MatcherSpecification method(HttpMethod method) {
        return method(method.toString());
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