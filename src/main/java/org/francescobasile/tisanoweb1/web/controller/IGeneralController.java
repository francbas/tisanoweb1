package org.francescobasile.tisanoweb1.web.controller;

import jakarta.servlet.ServletContext;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public interface IGeneralController {
    public void process(
            IWebExchange webExchange, ServletContext servletContext,
            ITemplateEngine templateEngine, Writer writer
    );
}
