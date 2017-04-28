package com.github.helpermethod.membrane.starter.controller;

import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MembraneHandlerMapping extends AbstractUrlHandlerMapping {
    private final List<String> prefixPaths;
    private final List<String> regexPaths;
    private final MembraneController membraneController;
    private volatile boolean dirty = true;

    public MembraneHandlerMapping(List<String> prefixPaths, List<String> regexPaths, MembraneController membraneController) {
        this.prefixPaths = prefixPaths;
        this.regexPaths = regexPaths;
        this.membraneController = membraneController;
    }

    @Override
    protected Object lookupHandler(String urlPath, HttpServletRequest request) throws Exception {
        if (dirty) {
            synchronized (this) {
                if (dirty) {
                    registerHandlers();
                    dirty = false;
                }
            }
        }

        return super.lookupHandler(urlPath, request);
    }

    private void registerHandlers() {
        prefixPaths.forEach(p -> registerHandler(p + "**", membraneController));
    }
}