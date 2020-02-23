package com.sonic.jpa.test;

import com.sonic.jpa.helloworld.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * JPATest
 *
 * @author Sonic
 * @since 2020/2/23
 */
public class JPATest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void destroy() {
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    //    hibernate 中 delete 方法, 只能移除持久化对象， 不能移除游离对象 （java.lang.IllegalArgumentException）
    @Test
    public void testRemove() {
//        Customer customer = new Customer();
//        JPA只能移除持久化对象， 不能移除游离对象 （java.lang.IllegalArgumentException）, hibernate 都可以
//        customer.setId(2);
        Customer customer2 = entityManager.find(Customer.class, 2);
        entityManager.remove(customer2);
    }

    //    hibernate 中 save 方法
    @Test
    public void testPersistence() {
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setEmail("Sky@qq.com");
        customer.setLastName("Sky");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
//      JPA save 对象 id 要为空 （javax.persistence.PersistenceException）， 但hibernate可以
//        customer.setId(100);
        entityManager.persist(customer);
        System.out.println("customer id: " + customer.getId());
    }

    //    hibernate 中 Session 的 load 方法
    @Test
    public void testGetReference() {
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println("customer class: " + customer.getClass().getName());
        System.out.println("----------------------------------------------");

//        org.hibernate.LazyInitializationException: could not initialize proxy [com.sonic.jpa.helloworld.Customer#1] - no Session
//        entityTransaction.commit();
//        entityManager.close();

        System.out.println("customer: " + customer);
    }

    // hibernate 中 Session 的 get 方法
    @Test
    public void testFind() {
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println("----------------------------------------------");
        System.out.println("customer: " + customer);
    }

}
