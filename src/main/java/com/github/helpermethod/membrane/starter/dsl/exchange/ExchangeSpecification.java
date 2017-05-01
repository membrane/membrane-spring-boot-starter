package com.github.helpermethod.membrane.starter.dsl.exchange;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.Outcome;

import java.util.Map;

public class ExchangeSpecification {
    private final Exchange exchange;

    public ExchangeSpecification(Exchange exchange) {
        this.exchange = exchange;
    }

    public RequestSpecification request() {
        return new RequestSpecification(exchange.getRequest());
    }

    public ResponseSpecification response() {
        return new ResponseSpecification(exchange.getResponse());
    }

    public Map<String, Object> properties() {
        return exchange.getProperties();
    }

    public Object property(String key) {
        return exchange.getProperty(key);
    }

    public ExchangeSpecification property(String key, Object value) {
        exchange.setProperty(key, value);

        return this;
    }

    public Exchange get() {
        return exchange;
    }

    @Override
    public String toString() {
        return exchange.toString();
    }
}