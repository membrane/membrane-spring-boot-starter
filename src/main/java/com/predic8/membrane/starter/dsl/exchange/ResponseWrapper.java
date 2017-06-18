package com.predic8.membrane.starter.dsl.exchange;

public class ResponseWrapper {
    private final com.predic8.membrane.core.http.Response response;

    public ResponseWrapper(com.predic8.membrane.core.http.Response response) {
        this.response = response;
    }

    public Status status() {
        return new Status(response.getStatusCode(), response.getStatusMessage());
    }

    public ResponseWrapper status(Status status) {
        this.response.setStatusCode(status.code());
        this.response.setStatusMessage(status.message());

        return this;
    }

    public com.predic8.membrane.core.http.Response unwrap() {
        return response;
    }
}