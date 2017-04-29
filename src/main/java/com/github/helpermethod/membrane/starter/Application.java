package com.github.helpermethod.membrane.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return proxies -> proxies
            .serviceProxy(s -> s
                .matches(m -> m.pathRegex("/(rest)?names.*"))
                .interceptors(i -> i
                    .rewriter(r -> r.map("^/names/(.*)", "/restnames/name\\.groovy\\?name=$1"))
                )
                .target(t -> t
                    .host("www.thomas-bayer.com")
                    .port(80)));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}