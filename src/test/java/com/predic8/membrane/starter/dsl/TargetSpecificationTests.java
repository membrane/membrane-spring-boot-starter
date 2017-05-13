package com.predic8.membrane.starter.dsl;

import com.predic8.membrane.core.rules.AbstractServiceProxy.Target;
import com.predic8.membrane.starter.dsl.TargetSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A TargetSpecification")
public class TargetSpecificationTests {
    private Target target;
    private TargetSpecification targetSpecification;

    @BeforeEach
    public void setUp() {
        target = new Target();
        targetSpecification = new TargetSpecification(target);
    }

    @Test
    @DisplayName("should set the host on the target")
    public void testSetHost() {
        targetSpecification.host("localhost");

        assertThat(target.getHost()).isEqualTo("localhost");
    }

    @Test
    @DisplayName("should set the port on the target")
    public void testSetPort() {
        targetSpecification.port(8080);

        assertThat(target.getPort()).isEqualTo(8080);
    }
}