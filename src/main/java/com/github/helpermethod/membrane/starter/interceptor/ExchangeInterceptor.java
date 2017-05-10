package com.github.helpermethod.membrane.starter.interceptor;


import com.github.helpermethod.membrane.starter.dsl.FlowSpecification;
import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.AbstractInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExchangeInterceptor extends AbstractInterceptor {
    private final BiFunction<Exchange, FlowSpecification, Outcome> c;

    public ExchangeInterceptor(BiFunction<Exchange, FlowSpecification, Outcome> c) {
        this.c = c;
    }

    @Override
    public Outcome handleRequest(Exchange exc) throws Exception {
        return c.apply(exc, new FlowSpecification(Flow.REQUEST));
    }

    @Override
    public Outcome handleResponse(Exchange exc) throws Exception {
        return c.apply(exc, new FlowSpecification(Flow.RESPONSE));
    }
}