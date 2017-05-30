package com.predic8.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.LogInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.predic8.membrane.core.interceptor.LogInterceptor.Level.WARN;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A LogSpecification")
class LogSpecificationTests {
    private LogInterceptor logInterceptor;
    private LogSpecification logSpecification;

    @BeforeEach
    void setUp() {
        logInterceptor = new LogInterceptor();
        logSpecification = new LogSpecification(logInterceptor);
    }

    @Test
    @DisplayName("should set the log level on the log interceptor")
    void testSetLevel() {
        logSpecification.level(WARN);

        assertThat(logInterceptor.getLevel()).isSameAs(WARN);
    }

    @Test
    @DisplayName("should set the category on the log interceptor")
    void testSetCategory() {
        logSpecification.category("logSpecification");

        assertThat(logInterceptor.getCategory()).isEqualTo("logSpecification");
    }
}