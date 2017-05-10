package com.github.helpermethod.membrane.starter.dsl;

import com.predic8.membrane.core.rules.AbstractServiceProxy.Target;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TargetSpecificationTests {
    private final Target target = new Target();
    private final TargetSpecification targetSpecification = new TargetSpecification(target);

    @Test
    @DisplayName("Set the host")
    public void testSetHost() {
        targetSpecification.host("localhost");

        assertThat(target.getHost()).isEqualTo("localhost");
    }

    @Test
    @DisplayName("Set the port")
    public void testSetPort() {
        targetSpecification.port(8080);

        assertThat(target.getPort()).isEqualTo(8080);
    }
}