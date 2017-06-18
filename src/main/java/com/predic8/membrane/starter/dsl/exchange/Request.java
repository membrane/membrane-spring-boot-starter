package com.predic8.membrane.starter.dsl.exchange;

public class Request {
    private final com.predic8.membrane.core.http.Request request;

    public Request(com.predic8.membrane.core.http.Request request) {
        this.request = request;
    }

    public String method() {
        return request.getMethod();
    }

    public String protocol() {
        return request.getVersion();
    }

    public com.predic8.membrane.core.http.Request unwrap() {
        return request;
    }
}