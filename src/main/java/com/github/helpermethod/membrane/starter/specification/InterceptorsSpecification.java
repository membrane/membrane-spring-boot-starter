package com.github.helpermethod.membrane.starter.specification;

import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.LogInterceptor;

import java.util.List;

public class InterceptorsSpecification {
    private final List<Interceptor> interceptors;

    public InterceptorsSpecification(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public InterceptorsSpecification log() {
        interceptors.add(new LogInterceptor());

        return this;
    }
}
