package org.francescobasile.tisanoweb1.web.controller;

import jakarta.ejb.Stateless;
import jakarta.servlet.ServletContext;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

@Stateless
//@Model
public class TestController implements IGeneralController {
    public TestController() {
    }

    @Override
    public void process(IWebExchange webExchange, ServletContext servletContext, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("messaggio", "TEST TEMPLATE ENGINE");
        templateEngine.process("dashboard3.html", ctx, writer);
//        templateEngine.process("_starter_template", ctx, writer);
    }
}
