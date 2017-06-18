package com.predic8.membrane.starter.dsl.exchange;

import org.springframework.http.HttpMethod;

import java.net.URI;

public class RequestWrapper {
    private final com.predic8.membrane.core.http.Request request;

    public RequestWrapper(com.predic8.membrane.core.http.Request request) {
        this.request = request;
    }

    public HttpMethod method() {
        return HttpMethod.valueOf(request.getMethod());
    }

    public URI uri() {
        return URI.create(request.getUri());
    }

    public RequestWrapper uri(URI uri) {
        request.setUri(uri.toString());

        return this;
    }

    public com.predic8.membrane.core.http.Request unwrap() {
        return request;
    }
}