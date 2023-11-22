package org.francescobasile.tisanoweb1.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.francescobasile.tisanoweb1.util.TextWrap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebFilter
//        (
//        displayName = "LoggerRequestFilter",
//        urlPatterns = {
//                "/LoggerRequestFilter"
//        },
//        initParams = {
//                @WebInitParam(name = "param1", value = "value1"),
//        },
//        dispatcherTypes = {
//                DispatcherType.REQUEST, DispatcherType.FORWARD
//        }
//)
public class LoggerRequestFilter implements Filter {

    private ServletContext servletContext;

    private final Logger logger = Logger.getLogger(LoggerRequestFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        logger.log(Level.INFO, TextWrap.leadAndTrail("Inizializzazione {0}"), this.getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.log(Level.FINEST, TextWrap.leadAndTrail("Performing Authentication request"));
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String m = params.nextElement();
            String v = req.getParameter(m);
            logger.info(TextWrap.leadAndTrail(req.getRemoteAddr() + "::Req param::{" + m + "=" + v + "}"));
        }
        List<Cookie> cookies = Arrays.stream(req.getCookies()).toList();
        cookies.forEach(c -> {
            if (!Objects.isNull(c))
                logger.info(TextWrap.leadAndTrail(req.getRemoteAddr() + "::Cookie::{" + c.getName() + "=" + c.getValue() + "}"));
        });
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
