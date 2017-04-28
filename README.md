# Membrane Spring Boot Starter

A Spring Boot Starter for Membrane Service Proxy

```java
@EnableMembrane
@SpringBootApplication
public class Application {
    @Bean
    public ProxiesConfiguration proxies() {
        return proxies -> proxies
            .serviceProxy(proxy -> proxy
                .matches(matcher -> matcher.path("/restnames/"))
                .target(target -> target.host("www.thomas-bayer.com")));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
