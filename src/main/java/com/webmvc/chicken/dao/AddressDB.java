package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.AddressEntity;
import com.webmvc.chicken.model.CustomerEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class AddressDB {

    private AddressDB() {

    }

    public static AddressEntity getAddressByCustomerId(int id) {
        CustomerEntity customer = CustomerDB.getCustomerById(id);
        return getAddressByCustomer(customer);
    }

    public static AddressEntity getAddressByCustomer(CustomerEntity customer) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<AddressEntity> address;
        AddressEntity results = null;

        try {
            address = em.createQuery("select a from AddressEntity a where a.customerId = ?1", AddressEntity.class);
            address.setParameter(1, customer);
            results = address.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        return results;
    }

    public static void insert(AddressEntity address) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(address);
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

    public static void update(AddressEntity address) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(address);
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
