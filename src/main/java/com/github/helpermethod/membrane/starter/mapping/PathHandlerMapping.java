package com.github.helpermethod.membrane.starter.mapping;

import com.github.helpermethod.membrane.starter.controller.MembraneController;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.BiPredicate;

public class PathHandlerMapping extends AbstractUrlHandlerMapping {
    private final List<String> paths;
    private BiPredicate<String, String> predicate;
    private final MembraneController membraneController;
    private volatile boolean dirty = true;

    public PathHandlerMapping(List<String> paths, BiPredicate<String, String> predicate, MembraneController membraneController) {
        this.paths = paths;
        this.predicate = predicate;
        this.membraneController = membraneController;
    }

    @Override
    protected Object lookupHandler(String urlPath, HttpServletRequest request) throws Exception {
        if (dirty) {
            synchronized (this) {
                if (dirty) {
                    paths.forEach(p -> registerHandler(p, membraneController));
                    dirty = false;
                }
            }
        }

        if (paths.stream().anyMatch(p -> predicate.test(urlPath, p))) {
            return membraneController;
        }

        return null;
    }
}