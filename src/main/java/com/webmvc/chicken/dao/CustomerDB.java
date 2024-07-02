package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.CustomerEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CustomerDB {

    private CustomerDB() {

    }

    public static CustomerEntity getCustomerByEmail(String email) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.mail = ?1", CustomerEntity.class);
            customer.setParameter(1, email);
            results = customer.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

    public static CustomerEntity getCustomerById(int id) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.customerId = ?1", CustomerEntity.class);
            customer.setParameter(1, id);
            results = customer.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

    public static CustomerEntity getCustomerByEmailAndPasswd(String email, String passwd) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.mail = ?1 and c.passwd = ?2", CustomerEntity.class);
            customer.setParameter(1, email);
            customer.setParameter(2, passwd);
            results = customer.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return results;
    }

    public static void insert(CustomerEntity customer) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(customer);
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

    public static void update(CustomerEntity customer) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(customer);
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
