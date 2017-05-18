package com.predic8.membrane.starter;

import com.predic8.membrane.starter.dsl.ProxiesSpecification;

@FunctionalInterface
public interface Proxies {
    void consume(ProxiesSpecification proxies);
}