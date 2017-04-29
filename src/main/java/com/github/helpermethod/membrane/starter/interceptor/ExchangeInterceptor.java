package com.github.helpermethod.membrane.starter.interceptor;


import com.github.helpermethod.membrane.starter.dsl.exchange.ExchangeSpecification;
import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.AbstractInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;

import java.util.function.Consumer;

public class ExchangeInterceptor extends AbstractInterceptor {
    private final Consumer<ExchangeSpecification> c;

    public ExchangeInterceptor(Consumer<ExchangeSpecification> c) {
        this.c = c;
    }

    @Override
    public Outcome handleRequest(Exchange exc) throws Exception {
        c.accept(new ExchangeSpecification(exc));

        return Outcome.CONTINUE;
    }

    @Override
    public Outcome handleResponse(Exchange exc) throws Exception {
        c.accept(new ExchangeSpecification(exc));

        return Outcome.CONTINUE;
    }
}