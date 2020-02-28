package com.sonic.jpa.test;

import com.sonic.jpa.helloworld.*;
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

//    /**
//     * 保存多对一时，建议先保存1，后保存n，这样不会多出额外的update语句
//     */
//    @Test
//    public void testManyToOnePersist() {
//        Customer customer = new Customer();
//
//        customer.setAge(15);
//        customer.setEmail("hh@qq.com");
//        customer.setLastName("hh");
//        customer.setCreatedTime(new Date());
//        customer.setBirth(new Date());
//
//        Order order1 = new Order();
//        order1.setOrderName("O-hh-1");
//
//        Order order2 = new Order();
//        order2.setOrderName("O-hh-2");
//
//        order1.setCustomer(customer);
//        order2.setCustomer(customer);
//
//        entityManager.persist(customer);
//        entityManager.persist(order1);
//        entityManager.persist(order2);
//
//    }
//
//    /**
//     * 左外连接
//     */
//    @Test
//    public void testManyToOneFind() {
////        Customer customer = entityManager.find(Customer.class, 11);
////        System.out.println(customer.getEmail());
//        Order order = entityManager.find(Order.class, 1);
//        System.out.println(order.getOrderName());
//        System.out.println(order.getCustomer().getEmail());
//    }
//   // 删除多的一方只能删除多的一条，如果配置了级联，则是把所有外键相关的记录都删掉
//    @Test
//    public void testManyToOneRemove(){
//        Order order = entityManager.find(Order.class, 2);
////  Wrong
////        Order order = new Order();
////        order.setId(8);
//        entityManager.remove(order);
//    }
//
//    @Test
//    public void testManyToOneUpdate() {
//        Order order = entityManager.find(Order.class, 1);
//        order.getCustomer().setLastName("Sky222");
//    }

    /**
     * 单向1-n ：一定会多出update语句
     * 因为多的一端不维护关联关系（外键），所以不会插入外键列，所以会多出update语句
     */
    @Test
    public void testOneToManyPersist() {
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setEmail("QQ@qq.com");
        customer.setLastName("QQ");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());

        Order order1 = new Order();
        order1.setOrderName("O-QQ-1");

        Order order2 = new Order();
        order2.setOrderName("O-QQ-2");

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    /**
     * 默认使用懒加载
     */
    @Test
    public void testOneToManyFind() {
        Customer customer = entityManager.find(Customer.class, 18);
        System.out.println(customer.getLastName());
        System.out.println(customer.getOrders());
    }

    /**
     * 删除1的一端，则会把关联n的一端的外键置空，然后进行删除
     * 通过{@link CascadeType.REMOVE},级联删除
     */
    @Test
    public void testOneToManyRemove() {
        Customer customer = entityManager.find(Customer.class, 19);
        entityManager.remove(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = entityManager.find(Customer.class, 17);
        customer.getOrders().iterator().next().setOrderName("O-fix-1");
    }

    /**
     * 由于双向维护关联关系，和插入顺序有关
     * 先插入n，再插入1，会多出n update
     * 先插入1，再插入n，会多出来2n update
     * 使用{@code @MappedBy}就能使用{@code @JoinColumn}
     * 建议（减少多余的SQL语句）：
     * 1. 使用n的一方维护关联关系，1的一方不维护关联关系，这样插入的时候外键就可以直接插入，而不需要update
     * 2. 插入的顺序：先插入1，再插入多
     */
    @Test
    public void testOneToManyPersistBothSide() {
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setEmail("YYY@qq.com");
        customer.setLastName("YYY");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());

        Order order1 = new Order();
        order1.setOrderName("O-YYY-1");

        Order order2 = new Order();
        order2.setOrderName("O-YYY-2");

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);

    }

    /**
     * 维护关联关系的一方一定要设置对象，这样外键才可以插入。
     * department.setMgr(manager);
     * 不维护关联关系的一方，可以不设置对象
     */
    @Test
    public void testOneToOnePersistence() {
        Manager manager = new Manager();
        manager.setMgrName("M-DD");

        Department department = new Department();
        department.setDeptName("D-DD");

//        manager.setDept(department);
        department.setMgr(manager);

        entityManager.persist(manager);
        entityManager.persist(department);

    }

    /**
     * 如果是lazy的情况下，
     * 如果查询维护外键的一方，查询另一个对象，只需要使用外键去查询
     * 如果查询不维护外键的一方，查询另一个对象，则需要外连接去查询
     */
    @Test
    public void testOneToOneFind() {
//        left join
        Department department = entityManager.find(Department.class, 1);
        System.out.println(department.getDeptName());
        System.out.println(department.getMgr().getClass().getName());
        System.out.println(department.getMgr().getMgrName());

//        left join
//        Manager manager = entityManager.find(Manager.class, 1);
//        System.out.println(manager.getMgrName());
//        System.out.println(manager.getDept().getClass().getName());
//        System.out.println(manager.getDept().getDeptName());

    }

    @Test
    public void testManyToManyPersistence() {
        Item i1 = new Item();
        i1.setItemName("i-1");

        Item i2 = new Item();
        i2.setItemName("i-2");

        Category c1 = new Category();
        c1.setCategoryName("c-1");

        Category c2 = new Category();
        c2.setCategoryName("c-2");

        i1.getCategories().add(c1);
        i1.getCategories().add(c2);

        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

//        c1.getItems().add(i1);
//        c1.getItems().add(i2);
//
//        c2.getItems().add(i1);
//        c2.getItems().add(i2);

        entityManager.persist(i1);
        entityManager.persist(i2);
        entityManager.persist(c1);
        entityManager.persist(c2);

    }

    /**
     * 无论获取哪边数据，都是先获取当前表，再用中间表left join另一边表获取信息
     */
    @Test
    public void testManyToManyFind() {
//        Item item = entityManager.find(Item.class, 1);
//        System.out.println(item.getItemName());
//        System.out.println(item.getCategories().size());

        Category category = entityManager.find(Category.class, 1);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());

    }

    @Test
    public void testSecondLevelCache() {
//        JPA 一级缓存
//        Customer customer1 = entityManager.find(Customer.class, 1);
//        Customer customer2 = entityManager.find(Customer.class, 1);

        Customer customer1 = entityManager.find(Customer.class, 1);

        entityTransaction.commit();
        entityManager.close();

        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer2 = entityManager.find(Customer.class, 1);

    }

}
