package com.github.helpermethod.membrane.starter.specification.interceptors;

import com.predic8.membrane.core.interceptor.LogInterceptor;

public class LogSpecification {
    private final LogInterceptor log;

    public LogSpecification(LogInterceptor log) {
        this.log = log;
    }

    public void level(LogInterceptor.Level level) {
        log.setLevel(level);
    }

    public void category(String category) {
        log.setCategory(category);
    }
}