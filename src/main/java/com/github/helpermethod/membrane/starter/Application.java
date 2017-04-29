package com.github.helpermethod.membrane.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

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
                    .exchange(exc ->
                        System.out.println(Arrays.toString(exc.request().headers().raw().getAllHeaderFields()))
                    )
                )
                .target(t -> t
                    .host("www.thomas-bayer.com")
                    .port(80)));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}