package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.config.Path;
import com.predic8.membrane.core.rules.ServiceProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

@DisplayName("A MatcherSpecification")
class MatcherSpecificationTests {
    private ServiceProxy serviceProxy;
    private MatcherSpecification matcherSpecification;

    @BeforeEach
    void setUp() {
        serviceProxy = new ServiceProxy();
        matcherSpecification = new MatcherSpecification(serviceProxy);
    }

    @Test
    @DisplayName("should set the method on the service proxy")
    void testSetMethod() {
        matcherSpecification.method("GET");

        assertThat(serviceProxy.getMethod()).isEqualTo("GET");
    }

    @Test
    @DisplayName("should set the method on the service proxy using an enum value")
    void testSetMethodUsingEnum() {
        matcherSpecification.method(GET);

        assertThat(serviceProxy.getMethod()).isEqualTo("GET");
    }

    @Test
    @DisplayName("should set the path pathPrefix on the service proxy")
    void testSetPathPrefix() {
        matcherSpecification.pathPrefix("/api/");

        assertThat(serviceProxy.getPath()).isEqualToComparingFieldByField(new Path(false, "/api/"));
    }

    @Test
    @DisplayName("should set the path pathRegex on the service proxy")
    void testSetPathRegex() {
        matcherSpecification.pathRegex("^/api/.*$");

        assertThat(serviceProxy.getPath()).isEqualToComparingFieldByField(new Path(true, "^/api/.*$"));
    }
}