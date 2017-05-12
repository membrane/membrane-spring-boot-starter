package com.github.helpermethod.membrane.starter.dsl.interceptors;

import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Mapping;
import com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Type.REDIRECT_TEMPORARY;
import static com.predic8.membrane.core.interceptor.rewrite.RewriteInterceptor.Type.REWRITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.params.provider.ObjectArrayArguments.*;

@DisplayName("A RewriterSpecification")
class RewriterSpecificationTests {

    @ParameterizedTest
    @MethodSource(names = "mapProvider")
    @DisplayName("should add a mapping to the list of rewrite interceptor mappings")
    void testMap(String from, String to, Type action) {
        ArrayList<Mapping> mappings = new ArrayList<>();
        RewriterSpecification rewriterSpecification = new RewriterSpecification(mappings);
        rewriterSpecification.map(from, to);

        assertThat(mappings).hasSize(1)
                            .extracting("from", "to", "do")
                            .contains(tuple(from, to, action));
    }

    static Stream<Arguments> mapProvider() {
        return Stream.of(
            create("http://www.predic8.de", "http://www.p8.de", REDIRECT_TEMPORARY),
            create("http://www.predic8.de", "www.p8.de", REWRITE));
    }
}