package com.github.helpermethod.membrane.starter.dsl.exchange;

import com.predic8.membrane.core.http.Header;

public class HeaderSpecification {
    private final Header header;

    public HeaderSpecification(Header header) {
        this.header = header;
    }

    public Header raw() {
        return header;
    }
}