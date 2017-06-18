package com.predic8.membrane.starter.dsl.exchange;

public class Status {
    private final int code;
    private final String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}