package org.francescobasile.tisanoweb1.web.controller;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import org.francescobasile.tisanoweb1.entity.User;
import org.francescobasile.tisanoweb1.util.persistance.JpaUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    //    @PersistenceContext(unitName = "tisanoPU")
    private EntityManager entityManager;
    //
    @Resource
    private UserTransaction userTransaction;
    private String message;

    public void init() {
//        this.entityManager = JpaUtils.getEntityManager();
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User("username02", "password02");
        User user2 = null;

        try {
            userTransaction.begin();
            JpaUtils.getEntityManager().persist(user);
            userTransaction.commit();
            user2 = JpaUtils.getEntityManager().find(User.class, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!Objects.isNull(user2)) {
            this.message = user.getUsername();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}