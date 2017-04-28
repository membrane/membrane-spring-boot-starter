package com.github.helpermethod.membrane.starter.mapping;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RegexHandlerMapping extends AbstractUrlHandlerMapping {
    private final List<String> regexPaths;
    private final MembraneController membraneController;
    private volatile boolean dirty = true;

    public RegexHandlerMapping(List<String> regexPaths, MembraneController membraneController) {
        this.regexPaths = regexPaths;
        this.membraneController = membraneController;
    }

    @Override
    protected Object lookupHandler(String urlPath, HttpServletRequest request) throws Exception {
        if (dirty) {
            synchronized (this) {
                if (dirty) {
                    regexPaths.forEach(p -> registerHandler(p, membraneController));
                    dirty = false;
                }
            }
        }

        if (regexPaths.stream().anyMatch(urlPath::matches)) {
            return membraneController;
        }

        return null;
    }
}