package com.github.helpermethod.membrane.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return p -> p.serviceProxy(s ->
            s.matches(m ->
                m.path("/api")
                 .method("GET"))
             .interceptors(i ->
                 i.log())
             .target(t ->
                 t.host("localhost")
             ));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}