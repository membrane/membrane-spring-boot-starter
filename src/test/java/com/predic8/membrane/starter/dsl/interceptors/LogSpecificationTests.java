package com.predic8.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.starter.dsl.interceptors.LogSpecification;
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

    @Test
    @DisplayName("should set the log level on the log interceptor")
    public void testSetLevel() {
        logSpecification.level(WARN);

        assertThat(logInterceptor.getLevel()).isSameAs(WARN);
    }

    @Test
    @DisplayName("should set the category on the log interceptor")
    public void testSetCategory() {
        logSpecification.category("logSpecification");

        assertThat(logInterceptor.getCategory()).isEqualTo("logSpecification");
    }
}