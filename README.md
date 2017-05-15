# Membrane Spring Boot Starter

[![Release](https://jitpack.io/v/membrane/membrane-spring-boot-starter.svg)](https://jitpack.io/#membrane/membrane-spring-boot-starter)
[![CircleCI](https://circleci.com/gh/membrane/membrane-spring-boot-starter.svg?style=shield&circle-token=8c730ac71f3736480b6b713ff86fe8b17a14cfa3)](https://circleci.com/gh/membrane/membrane-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509/badge.svg?style=flat)](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509)
[![codecov](https://codecov.io/gh/membrane/membrane-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/membrane/membrane-spring-boot-starter)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://raw.githubusercontent.com/membrane/membrane-spring-boot-starter/master/LICENSE)

A Spring Boot Starter for [Membrane Service Proxy](https://github.com/membrane/service-proxy).

## Features

* seamless integration with Spring Boot
* typesafe Java 8 DSL for Service Proxy configuration

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
