package com.github.helpermethod.membrane.starter.dsl.exchange;

import com.predic8.membrane.core.http.Response;

public class ResponseSpecification {
    private final Response reponse;

    public ResponseSpecification(Response response) {
        this.reponse = response;
    }

    public Response raw() {
        return reponse;
    }
}