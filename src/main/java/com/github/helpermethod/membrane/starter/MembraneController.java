package com.github.helpermethod.membrane.starter;

import com.github.helpermethod.membrane.starter.servlet.MembraneServlet;
import org.springframework.web.servlet.mvc.ServletWrappingController;

public class MembraneController extends ServletWrappingController {
    public MembraneController() {
        setServletClass(MembraneServlet.class);
        setServletName("membrane");
        setSupportedMethods((String[]) null);
    }
}