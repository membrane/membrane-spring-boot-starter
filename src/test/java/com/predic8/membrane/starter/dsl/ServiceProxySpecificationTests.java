package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.rules.ServiceProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

@DisplayName("A ServiceProxySpecification")
class ServiceProxySpecificationTests {
    @Test
    void testAddMatcher() {
        ServiceProxy serviceProxy = new ServiceProxy();
        ServiceProxySpecification serviceProxySpecification = new ServiceProxySpecification(serviceProxy);

        serviceProxySpecification.matches(m -> m.method(GET));

        assertThat(serviceProxy.getMethod()).isEqualTo("GET");
    }
}