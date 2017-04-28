package com.github.helpermethod.membrane.starter.handler;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrefixHandlerMapping extends AbstractUrlHandlerMapping {
    private final List<String> prefixPaths;
    private final MembraneController membraneController;
    private volatile boolean dirty = true;

    public PrefixHandlerMapping(List<String> prefixPaths, MembraneController membraneController) {
        this.prefixPaths = prefixPaths;
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