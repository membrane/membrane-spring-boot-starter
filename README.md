# Membrane Spring Boot Starter

[![CircleCI](https://circleci.com/gh/helpermethod/membrane-spring-boot-starter.svg?style=shield&circle-token=9ef92911f2b52bad3aef69791b1ea93a035d7d1a)](https://circleci.com/gh/helpermethod/membrane-spring-boot-starter)
[![codecov](https://codecov.io/gh/helpermethod/membrane-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/helpermethod/membrane-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/59109d639e070f003f7cb97c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/59109d639e070f003f7cb97c)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://raw.githubusercontent.com/helpermethod/membrane-spring-boot-starter/master/LICENSE)

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
