package com.sonic.service;

import com.sonic.bean.Coffee;
import com.sonic.bean.Student;
import com.sonic.mapper.CoffeeMapper;
import com.sonic.mapper.StudentMapper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TransactionService
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Service
public class RestService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CoffeeMapper coffeeMapper;

    public int insertStudent(){
        Student student = new Student();
        student.setId(1);
        student.setSname("Yuki");
        return studentMapper.insertStudent(student);
    }

    public int insertCoffee(){
        Coffee coffee = new Coffee();
        coffee.setId(1L);
        coffee.setName("KABUQINO");
        coffee.setPrice(Money.of(CurrencyUnit.USD, 1.0));
        return coffeeMapper.insertCoffee(coffee);
    }

    public List<Coffee> queryCoffee(){
        return coffeeMapper.queryCoffee();
    }



}
