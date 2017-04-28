package com.github.helpermethod.membrane.starter.specification;

import com.github.helpermethod.membrane.starter.specification.interceptors.LogSpecification;
import com.github.helpermethod.membrane.starter.specification.interceptors.RewriterSpecification;
import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor;

import java.util.List;
import java.util.function.Consumer;

public class InterceptorsSpecification {
    private final List<Interceptor> interceptors;

    public InterceptorsSpecification(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public InterceptorsSpecification log(Consumer<LogSpecification> c) {
        LogInterceptor logInterceptor = new LogInterceptor();
        c.accept(new LogSpecification(logInterceptor));

        interceptors.add(logInterceptor);

        return this;
    }

    public InterceptorsSpecification log() {
        return log(l -> {
        });
    }

    public InterceptorsSpecification rewriter(Consumer<RewriterSpecification> c) {
        RewriteInterceptor rewriteInterceptor = new RewriteInterceptor();
        c.accept(new RewriterSpecification(rewriteInterceptor.getMappings()));

        interceptors.add(rewriteInterceptor);

        return this;
    }
}
