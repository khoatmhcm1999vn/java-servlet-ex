package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.BillItemEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class BillItemDB {

    private BillItemDB() {

    }

    public static void insert(BillItemEntity item) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(item);
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
