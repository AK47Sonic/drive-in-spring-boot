package com.sonic.cache.bean;

public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer did;

    public Employee() {
        super();
    }

    public Employee(Integer id, String lastName, String email, Integer gender, Integer did) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.did = did;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

}
