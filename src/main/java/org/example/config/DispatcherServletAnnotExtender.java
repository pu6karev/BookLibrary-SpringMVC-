package org.example.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServletAnnotExtender implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) throws ServletException {

        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.scan("org.example");
        sc.addListener(new ContextLoaderListener(root));

         // --- DispatcherServlet initialization
        DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");

         // --- HiddenHttpMethodFilter registration
        FilterRegistration.Dynamic hiddenHttpMethodFilter = sc.addFilter(
                "hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}

