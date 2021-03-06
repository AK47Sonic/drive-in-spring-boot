package com.sonic.entity;

import javax.persistence.*;

/**
 * User
 *
 * @author Sonic
 * @since 2019/5/26
 */
@Entity // 告诉JPA，这是一个实体类（和数据表映射）
@Table(name = "tbl_user") // 和数据表对应，如果省略，默认表名就是类名小写user
public class User {

    @Id // 这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    private Integer id;

    @Column(name = "last_name", length = 50) // 这是和数据表对应的一个列
    private String lastName;
    @Column // 省略，默认列名就是属性名
    private String email;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
