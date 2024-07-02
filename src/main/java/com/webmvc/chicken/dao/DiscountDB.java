package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.DiscountEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DiscountDB {

    private DiscountDB() {

    }

    public static DiscountEntity getDiscountByCode(String code) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<DiscountEntity> customer;
        DiscountEntity results = null;
        try {
            customer = em.createQuery("select d from DiscountEntity d where d.discountCode = ?1", DiscountEntity.class);
            customer.setParameter(1, code);
            results = customer.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

}
