package com.webmvc.chicken.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("test");

    private HibernateUtil() {

    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

}
