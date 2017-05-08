# Membrane Spring Boot Starter

[![Dependency Status](https://www.versioneye.com/user/projects/59109d639e070f003f7cb97c/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59109d639e070f003f7cb97c)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?style=flat-square)]())](https://raw.githubusercontent.com/helpermethod/membrane-spring-boot-starter/master/LICENSE)

A Spring Boot Starter for [Membrane Service Proxy](https://github.com/membrane/service-proxy).

## Features

* seamless integration with Spring Boot
* typesafe Java 8 DSL for configuring Service Proxies

## Example

```java
@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return proxies -> proxies
            .serviceProxy(serviceProxy -> serviceProxy
                .matches(matcher -> matcher.pathPrefix("/restnames/"))
                .target(target -> target.host("www.thomas-bayer.com")));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
