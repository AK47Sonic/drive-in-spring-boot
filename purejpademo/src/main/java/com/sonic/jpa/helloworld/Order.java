package com.sonic.jpa.helloworld;

import javax.persistence.*;

/**
 * Order
 *
 * @author Sonic
 * @since 2020/2/24
 */
@Table(name = "jpa_orders")
@Entity
public class Order {

    private Integer id;

    private String orderName;

    private Customer customer;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ORDER_NAME")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 映射单向n-1的关联关系
     */
    @JoinColumn(name = "CUSTOMER_ID")
//    @ManyToOne(fetch = FetchType.LAZY) //懒加载
    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
