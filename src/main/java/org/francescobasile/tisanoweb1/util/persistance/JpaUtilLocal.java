package org.francescobasile.tisanoweb1.util.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaUtilLocal {
    static private String PERSISTANCE_UNIT = "tisanotest-pu"; // default provider
    static private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT);
    static private EntityManager entityManager = managerFactory.createEntityManager();

    public static EntityManager getEntityManager() {
        if (entityManager.isOpen()) return entityManager;
        managerFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT);
        entityManager = managerFactory.createEntityManager();
        return entityManager;
    }

    public static void registraPersistanceProvider(String pu) {
        PERSISTANCE_UNIT = pu;
    }

    public static <T> void save(T entity) {
        entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                throw e;
            }
        }
    }

    public static <T> void merge(T entity) {
        entityManager = getEntityManager();
        entityManager.merge(entity);
    }

    public static void close() {
        if (!entityManager.isOpen()) return;
        entityManager.close();
        managerFactory.close();
    }

    public static <T> T find(Class<T> classe, Object o) {
        entityManager = getEntityManager();
        return entityManager.find(classe, o);
    }

}
