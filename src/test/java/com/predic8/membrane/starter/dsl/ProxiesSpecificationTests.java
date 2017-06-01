package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.rules.ServiceProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A ProxiesSpecification")
class ProxiesSpecificationTests {
    @Test
    @DisplayName("should add a service proxy")
    void testAddServiceProxy() {
        ArrayList<ServiceProxy> serviceProxies = new ArrayList<>();
        ProxiesSpecification proxiesSpecification = new ProxiesSpecification(serviceProxies);

        proxiesSpecification.serviceProxy(s -> {});

        assertThat(serviceProxies).hasSize(1);
    }
}