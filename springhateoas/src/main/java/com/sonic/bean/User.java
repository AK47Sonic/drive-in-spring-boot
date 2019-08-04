package com.sonic.bean;

import org.springframework.hateoas.ResourceSupport;

/**
 * public class User {
 *
 * @author Sonic
 * @since 2019/8/3
 */
public class User extends ResourceSupport {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
