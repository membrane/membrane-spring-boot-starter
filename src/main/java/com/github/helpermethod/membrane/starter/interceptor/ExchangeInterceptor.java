package com.github.helpermethod.membrane.starter.interceptor;


import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.AbstractInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;

import java.util.function.Function;

public class ExchangeInterceptor extends AbstractInterceptor {
    private final Function<Exchange, Outcome> c;

    public ExchangeInterceptor(Function<Exchange, Outcome> c) {
        this.c = c;
    }

    @Override
    public Outcome handleRequest(Exchange exc) throws Exception {
        return c.apply(exc);
    }

    @Override
    public Outcome handleResponse(Exchange exc) throws Exception {
        return c.apply(exc);
    }
}