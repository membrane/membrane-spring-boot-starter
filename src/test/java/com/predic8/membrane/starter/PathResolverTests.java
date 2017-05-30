package com.predic8.membrane.starter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A PathResolver")
class PathResolverTests {
    @Test
    void prefixPaths() {
        PathResolver pathResolver = new PathResolver(singletonList("/api/"), emptyList());

        assertThat(pathResolver.prefixPaths()).containsOnly("/api/");
    }

    @Test
    void regexPaths() {
        PathResolver pathResolver = new PathResolver(emptyList(), singletonList("^/api/.*$"));

        assertThat(pathResolver.regexPaths()).containsOnly("^/api/.*$");
    }
}