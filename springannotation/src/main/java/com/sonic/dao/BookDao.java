package com.sonic.dao;

import org.springframework.stereotype.Repository;

/**
 * Repository
 *
 * @auther Sonic
 * @since 2018/12/15
 */
@Repository
//@Primary
public class BookDao {

    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
