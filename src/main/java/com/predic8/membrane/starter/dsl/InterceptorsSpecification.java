package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.flow.RequestInterceptor;
import com.predic8.membrane.core.interceptor.flow.ResponseInterceptor;

import java.util.List;
import java.util.function.Consumer;

public class InterceptorsSpecification extends TopLevelInterceptorsSpecification {
    public InterceptorsSpecification(List<Interceptor> interceptors) {
        super(interceptors);
    }

    public InterceptorsSpecification request(Consumer<TopLevelInterceptorsSpecification> c) {
        RequestInterceptor requestInterceptor = new RequestInterceptor();
        c.accept(new TopLevelInterceptorsSpecification(requestInterceptor.getInterceptors()));

        interceptors.add(requestInterceptor);

        return this;
    }

    public InterceptorsSpecification response(Consumer<TopLevelInterceptorsSpecification> c) {
        ResponseInterceptor responseInterceptor = new ResponseInterceptor();
        c.accept(new TopLevelInterceptorsSpecification(responseInterceptor.getInterceptors()));

        interceptors.add(responseInterceptor);

        return this;
    }
}