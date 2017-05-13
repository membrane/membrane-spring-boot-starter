package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.starter.dsl.interceptors.LogSpecification;
import com.predic8.membrane.starter.dsl.interceptors.RewriterSpecification;
import com.predic8.membrane.starter.interceptor.ExchangeInterceptor;
import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public InterceptorsSpecification interceptor(Interceptor interceptor) {
        interceptors.add(interceptor);

        return this;
    }

    public InterceptorsSpecification exchange(Function<Exchange, Outcome> c) {
        return exchange((exchange, flow) -> c.apply(exchange));
    }

    public InterceptorsSpecification exchange(BiFunction<Exchange, Interceptor.Flow, Outcome> c) {
        ExchangeInterceptor exchangeInterceptor = new ExchangeInterceptor(c);

        interceptors.add(exchangeInterceptor);

        return this;
    }
}