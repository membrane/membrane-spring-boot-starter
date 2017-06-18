package com.predic8.membrane.starter.dsl.exchange;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.http.Request;

import java.util.List;
import java.util.Map;

public class ExchangeSpecification {
    private final Exchange exchange;

    public ExchangeSpecification(Exchange exchange) {
        this.exchange = exchange;
    }

    public Request request() {
        return exchange.getRequest();
    }

    public List<String> destinations() {
        return exchange.getDestinations();
    }

    public Map<String, Object> properties() {
        return exchange.getProperties();
    }

    public Exchange unwrap() {
        return exchange;
    }
}