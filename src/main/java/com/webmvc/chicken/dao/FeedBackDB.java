package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.FeedbackEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class FeedBackDB {

    private FeedBackDB() {

    }

    public static void insert(FeedbackEntity feedback) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(feedback);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();
        }
    }

}
