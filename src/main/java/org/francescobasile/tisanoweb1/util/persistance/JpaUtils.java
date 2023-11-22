package org.francescobasile.tisanoweb1.util.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import javax.naming.InitialContext;

@PersistenceContext(name = "entityManager", unitName = "tisano-pu", type = PersistenceContextType.TRANSACTION)
public class JpaUtils {

    public static EntityManager getEntityManager() throws Exception {
        InitialContext context = new InitialContext();
        EntityManager entityManager = (EntityManager) context.lookup("java:comp/env/entityManager");
        if (entityManager == null) throw new RuntimeException("EntityManager not injected");
        return entityManager;
    }
}
