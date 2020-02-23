package com.sonic.jpa.test;

import com.sonic.jpa.helloworld.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;

/**
 * JPATest
 * 临时对象：自己创建的对象
 * 游离对象：有Id和数据库对应，但没有有持久化
 * 持久化对象：数据库返回的对象
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
//        entityManager.setFlushMode(FlushModeType.AUTO);
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void destroy() {
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    //    hibernate 中 saveOrUpadte 方法

    /**
     * 传入一个临时对象
     * 创建新对象，复制原对象属性去新对象，新对象进行持久化操作
     */
    @Test
    public void testMerge1() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setEmail("18@qq.com");
        customer.setLastName("18");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
        Customer customerMerge = entityManager.merge(customer);
        System.out.println("customer id: " + customer.getId());
        System.out.println("customerMerge id: " + customerMerge.getId());
    }

    /**
     * 传入一个游离对象（包含Id）
     * 1. JPA使用游离对象查询数据库(包括查cache和数据库）
     * 2. 若在EntityManager缓存中没有该对象
     * 3. 若在数据库中也没有对应的记录
     * 4. JPA会创建一个新对象， 然后把当前游离对象的属性复制到新创建的对象中(包括id）
     * 5. 对新创建的对象执行insert操作，返回一个对象并且再次通过反射调用所有set方法来更新自增id
     */
    @Test
    public void testMerge2() {
        Customer customer = new Customer();

        // set id
        customer.setId(20);

        customer.setAge(201);
        customer.setEmail("201@qq.com");
        customer.setLastName("201");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());

        Customer customerMerge = entityManager.merge(customer);
        System.out.println("customer id: " + customer.getId());
        System.out.println("customerMerge id: " + customerMerge.getId());
    }

    /**
     * 传入一个游离对象（包含Id）
     * 1. JPA使用游离对象查询数据库(包括查cache和数据库）
     * 2. 若在EntityManager缓存中没有该对象
     * 3. 若在数据库中也有对应的记录
     * 4. JPA查询到对应的记录， 然后返回该记录对应的对象（创建一个新对象），再把游离对象的属性复制到这个查询到的新对象中
     * 5. 对查询到的对象执行update操作
     */
    @Test
    public void testMerge3() {
        Customer customer = new Customer();
        // set id
        customer.setId(4);
        customer.setAge(21);
        customer.setEmail("21@qq.com");
        customer.setLastName("EE");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
        Customer customerMerge = entityManager.merge(customer);
        System.out.println("customer vs customerMerge: " + (customer == customerMerge));
    }

    /**
     * 传入一个游离对象（包含Id）
     * 1. JPA使用游离对象查询(包括查cache和数据库）
     * 2. 若在EntityManager缓存中有该对象
     * 4. JPA把游离对象的属性复制到EntityManager缓存对象中
     * 5. 对EntityManager缓存中的对象执行update操作
     */
    @Test
    public void testMerge4() {
        Customer customer = new Customer();
        // set id
        customer.setId(4);
        customer.setAge(30);
        customer.setEmail("30@qq.com");
        customer.setLastName("FF");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());

//        load customer to cache
        Customer customerFind = entityManager.find(Customer.class, 4);

        Customer customerMerge = entityManager.merge(customer);
        System.out.println("customer vs customerMerge: " + (customer == customerMerge));
    }

    /**
     * 同 hibernate 中 flush 方法
     * AUTO: 设置成auto之后，当程序进行查询、提交事务或者调用session.flush()的时候，都会使缓存和数据库进行同步，也就是刷新数据库。
     * COMMIT: 提交事务或者session.flush()时，刷新数据库；查询不刷新
     * ALWAYS: 每次进行查询、提交事务、session.flush()的时候都会刷数据库
     * JPA的FlushModeType只有两种：
     * 1、COMMIT：仅当提交事务时才能进行刷新
     * 2、AUTO：（默认）在执行查询时进行刷新
     */
    @Test
    public void testFlush() {
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer);
        customer.setLastName("PP1");

        entityManager.flush(); //刷新数据库
        System.out.println("before flush");
//        entityTransaction.rollback();
//        System.out.println("roll back");
    }

    /**
     * 同 hibernate 中 refresh
     * 强制再获取一次DB数据
     */
    @Test
    public void testRefresh() {
        Customer customer = entityManager.find(Customer.class, 1);
//        customer = entityManager.find(Customer.class, 1);

        entityManager.refresh(customer);
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
