package com.sonic.mapper;

import com.sonic.bean.Coffee;

import java.util.List;

/**
 * CoffeeMapper
 *
 * @author Sonic
 * @since 2019/4/22
 */
public interface CoffeeMapper {
    int insertCoffee(Coffee coffee);
    List<Coffee> queryCoffee();
}
