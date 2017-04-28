package com.github.helpermethod.membrane.starter.specification.interceptors;

import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor;

import java.util.List;

public class RewriterSpecification {
    private final List<RewriteInterceptor.Mapping> mappings;

    public RewriterSpecification(List<RewriteInterceptor.Mapping> mappings) {
        this.mappings = mappings;
    }

    public RewriterSpecification map(String from, String to, String action) {
        mappings.add(new RewriteInterceptor.Mapping(from, to, action));

        return this;
    }

    public RewriterSpecification map(String from, String to) {
        return map(from, to, null);
    }
}