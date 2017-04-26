package com.github.helpermethod.membrane.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return p ->
            p.serviceProxy(s ->
                s.matches(m ->
                     m.path("/restnames"))
                .target(t ->
                    t.host("www.thomas-bayer.com")
                ));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}