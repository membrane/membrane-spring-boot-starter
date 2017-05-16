# Membrane Spring Boot Starter

[![CircleCI](https://circleci.com/gh/membrane/membrane-spring-boot-starter.svg?style=shield&circle-token=8c730ac71f3736480b6b713ff86fe8b17a14cfa3)](https://circleci.com/gh/membrane/membrane-spring-boot-starter)
[![Release](https://jitpack.io/v/membrane/membrane-spring-boot-starter.svg)](https://jitpack.io/#membrane/membrane-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509/badge.svg?style=flat)](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509)
[![codecov](https://codecov.io/gh/membrane/membrane-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/membrane/membrane-spring-boot-starter)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://raw.githubusercontent.com/membrane/membrane-spring-boot-starter/master/LICENSE)

A Spring Boot Starter for [Membrane Service Proxy](https://github.com/membrane/service-proxy).

## Usage

```java
// autoconfigures Membrane Service Proxy
@EnableMembrane
@SpringBootApplication
public class Application {
    // service proxy configurations are defined as a Java 8 lambda expression
    @Bean
    public ProxiesConfiguration proxies() {
        return p -> p
            .serviceProxy(s -> s
                // match all requests starting with `/api/`
                .matches(m -> m.pathPrefix("/api/"))
                // log both request and response headers
                .interceptors(i -> i.log())
                .target(t -> t.host("swapi.co")));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## Features

* seamless integration with Spring Boot
* typesafe Java 8 DSL for Service Proxy configuration
* extendable through plugins

## Installation

### Maven

### Gradle

## Acknowledgements

Thanks @snicoll for pointing out errors in the autoconfiguration and adding the project to Spring Boot's list of [community contributed starters](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-starters)!
