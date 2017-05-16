package com.predic8.membrane.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembraneMarkerConfiguration {
    @Bean
    public Marker membraneMarkerBean() {
        return new Marker();
    }

    static class Marker {
    }
}