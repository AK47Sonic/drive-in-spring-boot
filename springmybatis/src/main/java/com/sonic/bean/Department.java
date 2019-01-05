package com.sonic.bean;

import org.apache.ibatis.type.Alias;

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
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
