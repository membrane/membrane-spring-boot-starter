package com.github.helpermethod.membrane.starter;

@FunctionalInterface
public interface ProxiesConfiguration {
    Proxies apply(Proxies proxies);
}