package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.rules.ServiceProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A ProxiesSpecification")
public class ProxiesSpecificationTests {
    @Test
    public void testAddServiceProxy() {
        ArrayList<ServiceProxy> serviceProxies = new ArrayList<>();
        ProxiesSpecification proxiesSpecification = new ProxiesSpecification(serviceProxies);

        proxiesSpecification.serviceProxy(s -> s
            .target(t -> t.host("localhost")));

        assertThat(serviceProxies).hasSize(1);
    }
}