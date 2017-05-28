package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor;
import com.predic8.membrane.starter.dsl.interceptors.LogSpecification;
import com.predic8.membrane.starter.dsl.interceptors.RewriterSpecification;
import com.predic8.membrane.starter.interceptor.ExchangeInterceptor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class NonFlowInterceptorsSpecification {
    protected List<Interceptor> interceptors;

    public NonFlowInterceptorsSpecification(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public NonFlowInterceptorsSpecification log(Consumer<LogSpecification> c) {
        LogInterceptor logInterceptor = new LogInterceptor();
        c.accept(new LogSpecification(logInterceptor));

        interceptors.add(logInterceptor);

        return this;
    }

    public NonFlowInterceptorsSpecification log() {
        return log(l -> {
        });
    }

    public NonFlowInterceptorsSpecification rewriter(Consumer<RewriterSpecification> c) {
        RewriteInterceptor rewriteInterceptor = new RewriteInterceptor();
        c.accept(new RewriterSpecification(rewriteInterceptor.getMappings()));

        interceptors.add(rewriteInterceptor);

        return this;
    }

    public NonFlowInterceptorsSpecification interceptor(Interceptor interceptor) {
        interceptors.add(interceptor);

        return this;
    }

    public NonFlowInterceptorsSpecification exchange(Function<Exchange, Outcome> c) {
        return exchange((exchange, flow) -> c.apply(exchange));
    }

    public NonFlowInterceptorsSpecification exchange(BiFunction<Exchange, Interceptor.Flow, Outcome> c) {
        ExchangeInterceptor exchangeInterceptor = new ExchangeInterceptor(c);

        interceptors.add(exchangeInterceptor);

        return this;
    }
}