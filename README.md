# Membrane Spring Boot Starter

[![CircleCI](https://circleci.com/gh/membrane/membrane-spring-boot-starter.svg?style=shield&circle-token=8c730ac71f3736480b6b713ff86fe8b17a14cfa3)](https://circleci.com/gh/membrane/membrane-spring-boot-starter)
[![Release](https://jitpack.io/v/membrane/membrane-spring-boot-starter.svg)](https://jitpack.io/#membrane/membrane-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509/badge.svg?style=flat)](https://www.versioneye.com/user/projects/59147f67e1638f00500b4509)
[![codecov](https://codecov.io/gh/membrane/membrane-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/membrane/membrane-spring-boot-starter)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://raw.githubusercontent.com/membrane/membrane-spring-boot-starter/master/LICENSE)

A Spring Boot Starter for [Membrane Service Proxy](https://github.com/membrane/service-proxy).

## Usage

```java
@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return p -> p
            .serviceProxy(s -> s
                .matches(m -> m.pathPrefix("/jokes/"))
                .interceptors(i -> i.log())
                .target(t -> t.host("api.icndb.com")));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

```sh
$ curl http://localhost:8080/jokes/random
```

## Installation

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.membrane</groupId>
        <artifactId>membrane-spring-boot-starter</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.membrane:membrane-spring-boot-starter:0.1.0'
}
```

## Acknowledgements

Thanks [@snicoll](https://github.com/snicoll) for pointing out flaws in the auto-configuration!
