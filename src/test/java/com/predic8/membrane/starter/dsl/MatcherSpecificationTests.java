package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.config.Path;
import com.predic8.membrane.core.rules.ServiceProxy;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@DisplayName("A MatcherSpecification")
public class MatcherSpecificationTests {
    private ServiceProxy serviceProxy;
    private MatcherSpecification matcherSpecification;

    @BeforeEach
    public void setUp() {
        serviceProxy = new ServiceProxy();
        matcherSpecification = new MatcherSpecification(serviceProxy);
    }

    @Test
    @DisplayName("should set the method on the service proxy")
    public void testSetMethod() {
        matcherSpecification.method("GET");

        assertThat(serviceProxy.getMethod()).isEqualTo("GET");
    }

    @Test
    @DisplayName("should set the path prefix on the service proxy")
    public void testSetPathPrefix() {
        matcherSpecification.pathPrefix("/api/");

        assertThat(serviceProxy.getPath()).isEqualToComparingFieldByField(new Path(false, "/api"));
    }

    @Test
    @DisplayName("should set the path regex on the service proxy")
    public void testSetPathRegex() {
        matcherSpecification.pathRegex("^/api/.*$");

        assertThat(serviceProxy.getPath()).isEqualToComparingFieldByField(new Path(true, "^/api/.*$"));
    }
}