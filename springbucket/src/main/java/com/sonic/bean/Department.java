package com.sonic.bean;

import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Department
 *
 * @auther Sonic
 * @since 2019/1/3
 */
@Alias("Department")
public class Department {

    private Integer id;
    private String departmentName;
    private List<Employee> employees;

    public Department(){}


    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", employees=" + employees +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
