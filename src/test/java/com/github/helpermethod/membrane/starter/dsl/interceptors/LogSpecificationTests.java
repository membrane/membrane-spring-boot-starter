package com.github.helpermethod.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.LogInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.predic8.membrane.core.interceptor.LogInterceptor.Level.WARN;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A LogSpecification")
public class LogSpecificationTests {
    private LogInterceptor logInterceptor;
    private LogSpecification logSpecification;

    @BeforeEach
    public void setUp() {
        logInterceptor = new LogInterceptor();
        logSpecification = new LogSpecification(logInterceptor);
    }

    @DisplayName("should set the log level on the log interceptor")
    @Test
    public void testSetLevel() {
        logSpecification.level(WARN);

        assertThat(logInterceptor.getLevel()).isSameAs(WARN);
    }

    @DisplayName("should set the category on the log interceptor")
    @Test
    public void testSetCategory() {
        logSpecification.category("logSpecification");

        assertThat(logInterceptor.getCategory()).isEqualTo("logSpecification");
    }
}