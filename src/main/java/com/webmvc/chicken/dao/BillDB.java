package com.webmvc.chicken.dao;

import com.webmvc.chicken.model.BillEntity;
import com.webmvc.chicken.model.CustomerEntity;
import com.webmvc.chicken.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Date;

public class BillDB {

    private BillDB() {

    }

    public static void insert(BillEntity bill) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(bill);
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

    public static void update(BillEntity bill) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(bill);
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

    public static int getCountBillToDate(Date toDate, CustomerEntity customer) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<BillEntity> bill;

        int results = 0;
        try {
            bill = em.createQuery("select b from BillEntity b where b.invoiceDate = ?1 and b.customer = ?2", BillEntity.class);
            bill.setParameter(1, toDate);
            bill.setParameter(2, customer);
            results = bill.getResultList().size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return results;
    }

}
