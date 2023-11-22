package org.francescobasile.tisanoweb1.web.filter;

import org.francescobasile.tisanoweb1.web.controller.IGeneralController;
import org.francescobasile.tisanoweb1.web.controller.TestController;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private static final Map<String, IGeneralController> mapperByUrl;

    static {
        mapperByUrl = new HashMap<>();
//        routerByUrl.put("/hello-servlet", new TestController());
        mapperByUrl.put("/test/filter", new TestController());
    }

    /**
     * Determina e restituisce il controller responsabile per l'esecuzione della Request.
     * la struttura dati del mapper contiene la mappa di corrispondenza tra URL della Request e il controller.
     * Se non trova la corrispondenza restituisce null ed il controllo viene passato alla chain successiva del filtro
     * {{@link RouterRequestFilter}}
     *
     * @param request
     * @return
     */
    public static IGeneralController registraControllerByRequest(final IWebRequest request) {
        final String route = getRoutePath(request);
        return mapperByUrl.get(route);
    }

    private static String getRoutePath(final IWebRequest request) {
        String pathRichiesto = request.getPathWithinApplication();

        final int partialIndex = pathRichiesto.indexOf(";");
        if (partialIndex != -1) {
            pathRichiesto = pathRichiesto.substring(0, partialIndex);
        }
        return pathRichiesto;
    }
}
