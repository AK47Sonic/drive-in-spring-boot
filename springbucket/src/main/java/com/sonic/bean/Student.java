package com.sonic.bean;

import org.apache.ibatis.type.Alias;

/**
 * Student
 *
 * @author Sonic
 * @since 2019/4/21
 */
@Alias("Student")
public class Student {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                '}';
    }

    private String sname;


}
