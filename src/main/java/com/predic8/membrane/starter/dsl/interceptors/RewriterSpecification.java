package com.predic8.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Mapping;

import java.util.List;

public class RewriterSpecification {
    private final List<Mapping> mappings;

    public RewriterSpecification(List<Mapping> mappings) {
        this.mappings = mappings;
    }

    public RewriterSpecification map(String from, String to, String action) {
        mappings.add(new Mapping(from, to, action));

        return this;
    }

    public RewriterSpecification map(String from, String to) {
        return map(from, to, null);
    }
}