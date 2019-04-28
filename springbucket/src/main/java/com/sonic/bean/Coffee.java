package com.sonic.bean;

import org.apache.ibatis.type.Alias;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

/**
 * Coffee
 *
 * @author Sonic
 * @since 2019/4/22
 */
@Alias("Coffee")
@Component
public class Coffee {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    private Money price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
