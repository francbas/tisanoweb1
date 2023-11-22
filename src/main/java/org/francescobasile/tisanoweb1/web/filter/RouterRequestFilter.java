package org.francescobasile.tisanoweb1.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.francescobasile.tisanoweb1.util.TextWrap;
import org.francescobasile.tisanoweb1.web.controller.IGeneralController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

//@WebFilter
//        (
//        displayName = "RouterRequestFilter",
//        urlPatterns = {
//                "/RouterRequestFilter"
//        },
//        initParams = {
//                @WebInitParam(name = "BASE_DIR", value = "/WEB-INF/views/"),
//                @WebInitParam(name = "SUFFIX", value = ".html")
//        },
//        dispatcherTypes = {
//                DispatcherType.REQUEST, DispatcherType.FORWARD
//        }
//)
public class RouterRequestFilter implements Filter {

    private ServletContext servletContext;
    private final Logger logger = Logger.getLogger(RouterRequestFilter.class.getName());

    private static String BASE_DIR; //""/WEB-INF/views/";
    private static String SUFFIX; //".html";


    private ITemplateEngine templateEngine;
    private JakartaServletWebApplication application;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        BASE_DIR = filterConfig.getInitParameter("BASE_DIR");
        SUFFIX = filterConfig.getInitParameter("SUFFIX");
        logger.log(Level.INFO, TextWrap.leadAndTrail("BASE_DIR {0}"), BASE_DIR);
        logger.log(Level.INFO, TextWrap.leadAndTrail("SUFFIX {0}"), SUFFIX);

        this.servletContext = filterConfig.getServletContext();
        this.application = JakartaServletWebApplication.buildApplication(filterConfig.getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
        logger.log(Level.INFO, TextWrap.leadAndTrail("Inizializzazione {0}"), this.getClass().getSimpleName());
    }

    /**
     * <p>
     * Implementazione del metodo di interfaccia Filter che passa il controllo alla successiva risorsa se il metodo
     * execute non risolve la corrispondenza di mappatura tra URL della request e un controller eventualemnte associato.
     * Questo filtro deve sempre trovarsi alla fine della catena per assicurare che il controller mappato gestisca
     * la response a valle di tutti i controlli dei filtri precedenti
     * </p>
     *
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!execute((HttpServletRequest) request, (HttpServletResponse) response, chain)) {
            logger.log(Level.INFO, TextWrap.leadAndTrail("Path non è mappato. Prosegue con doFilter"));
            chain.doFilter(request, response);
        }
        logger.log(Level.INFO, TextWrap.leadAndTrail("Filtro completato!"));
    }

    /**
     * <p>
     * Esegue il motore di filtro degli URL provenienti nell'intera applicazione. Il filtro risponde sul request-path
     * principale dell'applicazione (/*) ed deve essere sempre l'ultima risorsa da chiamare nella catena dei filtri.
     * Essendo l'ultima risorsa della catena determina il controller finale che si occupa di scrivere l'output nella
     * 'response'.
     * Se nessun controller risulta mappato alla request o alla request modificata da precedenti filtri, sebbene
     * quest'ultimo non dovrebbe accadere, passa il controllo alla risorsa statica index.jsp.
     * In caso di errore ha sempre priorità la pagina {err_code}.html definita nel deployment descriptor.
     * E' una implementazione del design pattern "Chain of Responsability".
     * </p>
     *
     * @param req
     * @param resp
     * @param chain
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected boolean execute(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {

        final IWebExchange webExchange = this.application.buildExchange(req, resp);
        final IWebRequest webRequest = webExchange.getRequest();

        if (Stream.of("/css", "/img", "/favicon").anyMatch(s -> webRequest.getPathWithinApplication().startsWith(s))) {
            return false;
        }
        IGeneralController controller = Router.registraControllerByRequest(webRequest);
        if (Objects.isNull(controller)) return false;

        logger.log(Level.INFO, TextWrap.leadAndTrail("Router trovato!"), controller.getClass().getSimpleName());

        resp.setContentType("text/html");
        resp.setHeader("Pragma", "no-setCacheable");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        final Writer writer = resp.getWriter();

        controller.process(webExchange, servletContext, this.templateEngine, writer);

        return true;
    }

    @Override
    public void destroy() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("Filtro destroyed!"));
    }

    private static ITemplateEngine buildTemplateEngine(final IWebApplication application) {

        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix(BASE_DIR);
        templateResolver.setSuffix(SUFFIX);
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
}
