package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.ViewProductEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ViewProductDB {

    private ViewProductDB() {

    }

    public static List<ViewProductEntity> getListProduct() {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewProductEntity> product;
        List<ViewProductEntity> results = null;
        try {
            product = em.createQuery("select p from ViewProductEntity p", ViewProductEntity.class);
            results = product.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

    public static ViewProductEntity getProductByCode(String code) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewProductEntity> product;
        ViewProductEntity results = null;
        try {
            product = em.createQuery("select p from ViewProductEntity p where p.productCode = ?1", ViewProductEntity.class);
            product.setParameter(1, code);
            results = product.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

}
