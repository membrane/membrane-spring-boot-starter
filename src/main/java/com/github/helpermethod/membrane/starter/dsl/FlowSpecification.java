package com.github.helpermethod.membrane.starter.dsl;

import com.predic8.membrane.core.interceptor.Interceptor;

public class FlowSpecification {
    private final Interceptor.Flow flow;

    public FlowSpecification(Interceptor.Flow flow) {
        this.flow = flow;
    }

    public boolean isRequest() {
        return flow == Interceptor.Flow.REQUEST;
    }

    public boolean isResponse() {
        return flow == Interceptor.Flow.RESPONSE;
    }

    public boolean isAbort() {
        return flow == Interceptor.Flow.ABORT;
    }
}