package com.sonic.jpa.helloworld;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer
 *
 * @author Sonic
 * @since 2020/2/23
 */
@Table(name = "jpa_customers")
@Entity
public class Customer {

    private Integer id;

    private String lastName;
    private String email;
    private int age;

    private Date createdTime;
    private Date birth;

    private Set<Order> orders = new HashSet<>();

    @JoinColumn(name = "CUSTOMER_ID")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Temporal(value = TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    //    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 默认auto是sequence,版本不同有差别
//    @TableGenerator(name = "id_generator", table = "jpa_id_generators",
//            pkColumnName = "pk_name", pkColumnValue = "customer_id", valueColumnName = "pk_value",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    /**
     * jpa_id_generators：
     *     id  pk_name      pk_value
     * ------  -----------  ----------
     *      3  order_id            100
     *      2  student_id           10
     *      1  customer_id           3
     */
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic // 默认加了
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Transient // 排除非mapping字段
    public String getInfo() {
        return "lastName: " + lastName + ", email: " + email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdTime=" + createdTime +
                ", birth=" + birth +
                '}';
    }
}
