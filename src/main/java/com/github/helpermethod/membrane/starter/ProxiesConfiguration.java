package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.specification.ProxiesSpecification;

@FunctionalInterface
public interface ProxiesConfiguration {
    void consume(ProxiesSpecification proxies);
}