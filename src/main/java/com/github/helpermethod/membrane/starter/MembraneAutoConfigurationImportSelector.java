package com.github.helpermethod.membrane.starter;

import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MembraneAutoConfigurationImportSelector extends AutoConfigurationImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] { "com.github.helpermethod.membrane.starter.MembraneAutoConfiguration" };
    }
}