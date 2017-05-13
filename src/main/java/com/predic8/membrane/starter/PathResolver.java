package com.predic8.membrane.starter;

import java.util.Collections;
import java.util.List;

class PathResolver {
    private final List<String> prefixPaths;
    private final List<String> regexPaths;

    PathResolver(List<String> prefixPaths, List<String> regexPaths) {
        this.prefixPaths = prefixPaths;
        this.regexPaths = regexPaths;
    }

    List<String> prefixPaths() {
        return Collections.unmodifiableList(prefixPaths);
    }

    List<String> regexPaths() {
        return Collections.unmodifiableList(regexPaths);
    }
}