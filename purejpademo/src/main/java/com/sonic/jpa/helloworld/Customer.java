package com.sonic.jpa.helloworld;

import javax.persistence.*;

/**
 * Customer
 *
 * @author Sonic
 * @since 2020/2/23
 */
@Table(name = "JPA_CUSTOMERS")
@Entity
public class Customer {

//    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String lastName;
    private String email;
    private int age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
}
