package com.predic8.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Mapping;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Type;

import java.util.List;

public class RewriterSpecification {
    private final List<Mapping> mappings;

    public RewriterSpecification(List<Mapping> mappings) {
        this.mappings = mappings;
    }

    public RewriterSpecification map(String from, String to, Type action) {
        mappings.add(createMapping(from, to, action));

        return this;
    }

    public RewriterSpecification map(String from, String to) {
        return map(from, to, null);
    }

    private Mapping createMapping(String from, String to, Type action) {
        Mapping mapping = new Mapping();
        mapping.setFrom(from);
        mapping.setTo(to);
        mapping.setDo(action != null ? action : mapping.getDo());

        return mapping;
    }
}