package com.predic8.membrane.starter.interceptor;


import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.AbstractInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;

import java.util.function.BiFunction;

public class ExchangeInterceptor extends AbstractInterceptor {
    private final BiFunction<Exchange, Flow, Outcome> c;

    public ExchangeInterceptor(BiFunction<Exchange, Flow, Outcome> c) {
        this.c = c;
    }

    @Override
    public Outcome handleRequest(Exchange exc) throws Exception {
        return c.apply(exc, Flow.REQUEST);
    }

    @Override
    public Outcome handleResponse(Exchange exc) throws Exception {
        return c.apply(exc, Flow.RESPONSE);
    }
}