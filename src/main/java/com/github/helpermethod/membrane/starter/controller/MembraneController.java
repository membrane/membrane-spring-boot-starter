package com.github.helpermethod.membrane.starter.controller;

import com.github.helpermethod.membrane.starter.servlet.MembraneServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembraneController extends ServletWrappingController {
    public MembraneController() {
        setServletClass(MembraneServlet.class);
        setServletName("membrane");
        setSupportedMethods((String[]) null);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleRequestInternal(request, response);
    }
}