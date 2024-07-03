package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.CategoriesEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriesDB {

    private CategoriesDB() {

    }

    public static List<CategoriesEntity> getListCategory() {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CategoriesEntity> category;
        List<CategoriesEntity> results = null;
        try {
            category = em.createQuery("select c from CategoriesEntity c", CategoriesEntity.class);
            results = category.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return results;
    }

}
