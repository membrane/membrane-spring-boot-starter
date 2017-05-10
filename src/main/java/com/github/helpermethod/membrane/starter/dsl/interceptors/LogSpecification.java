package com.github.helpermethod.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.interceptor.LogInterceptor.Level;

public class LogSpecification {
    private final LogInterceptor log;

    public LogSpecification(LogInterceptor log) {
        this.log = log;
    }

    public LogSpecification level(Level level) {
        log.setLevel(level);

        return this;
    }

    public LogSpecification category(String category) {
        log.setCategory(category);

        return this;
    }
}