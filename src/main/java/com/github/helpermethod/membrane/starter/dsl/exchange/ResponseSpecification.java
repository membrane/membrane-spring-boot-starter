package com.github.helpermethod.membrane.starter.dsl.exchange;

import com.predic8.membrane.core.http.Response;

public class ResponseSpecification {
    private final Response reponse;

    ResponseSpecification(Response response) {
        this.reponse = response;
    }

    public Response get() {
        return reponse;
    }

    public String toString() {
        return reponse.toString();
    }
}