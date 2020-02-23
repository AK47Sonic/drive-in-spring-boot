package com.sonic.jpa.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * MainApp
 *
 * @author Sonic
 * @since 2020/2/23
 */
public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setEmail("Sonic@qq.com");
        customer.setLastName("Sonic");
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
