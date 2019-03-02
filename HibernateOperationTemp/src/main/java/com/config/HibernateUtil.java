package com.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    private static Metadata config() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata;
    }

    private static void buildSessionFactory() {
        if (checkStatus()) {
            synchronized (HibernateUtil.class) {
                if (checkStatus()) {
                    sessionFactory = config().buildSessionFactory();
                }
            }
        }
    }

    private static Boolean checkStatus() {
        return sessionFactory == null ? true : false;
    }

    public static SessionFactory getSessionFactory() {
        if (checkStatus()) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session openSession() {
        if (checkStatus())
            buildSessionFactory();
        return sessionFactory.openSession();
    }

    public static void closeFactory() {
        sessionFactory.close();
    }
}
