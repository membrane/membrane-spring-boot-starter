package com.predic8.membrane.starter.dsl.exchange;

public class Response {
    private final com.predic8.membrane.core.http.Response response;

    public Response(com.predic8.membrane.core.http.Response response) {
        this.response = response;
    }

    public Status status() {
        return new Status(response.getStatusCode(), response.getStatusMessage());
    }

    public Response status(Status status) {
        this.response.setStatusCode(status.code());
        this.response.setStatusMessage(status.message());

        return this;
    }

    public String protocol() {
        return response.getVersion();
    }

    public com.predic8.membrane.core.http.Response unwrap() {
        return response;
    }
}