package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.CustomerEntity;
import com.webmvc.chicken.model.ProductEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ProductDB {

    private ProductDB() {

    }

    public static ProductEntity getProductByCode(String code) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> product;
        ProductEntity results = null;
        try {
            product = em.createQuery("select p from ProductEntity p where p.productCode = ?1", ProductEntity.class);
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

    public static void insert(ProductEntity product) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(product);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();
            emf.close();
        }
    }

    public static void update(ProductEntity product) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(product);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();
            emf.close();
        }
    }

    public static void delete(ProductEntity product) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(product);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();
            emf.close();
        }
    }

}
