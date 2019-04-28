package com.sonic.controller;

import com.sonic.bean.Coffee;
import com.sonic.service.RestService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TransactionController
 *
 * @author Sonic
 * @since 2019/4/20
 */
@RestController
public class RestDemoController {
    private final Logger logger = LoggerFactory.getLogger(RestDemoController.class);

    @Autowired
    private RestService restService;

    @GetMapping("/insert")
    public int insertData() {
        return restService.insertStudent();
    }

    @GetMapping("/insertCoffee")
    public int insertCoffee() {
        return restService.insertCoffee();
    }

    @GetMapping("/queryCoffee")
    public List<Coffee> queryCoffee() {
        return restService.queryCoffee();
    }

    @Autowired
    private Coffee coffee;

    private volatile int count;

    @GetMapping("/testCoffee")
    public Coffee testCoffee(){
        logger.info("----------------" + ++count);
        logger.info("Before coffee: " + coffee);
        logger.info("Before coffee class: " + coffee.getClass().getName());
        coffee.setPrice(Money.of(CurrencyUnit.USD, 100));
        logger.info("After coffee: " + coffee);
        logger.info("After coffee class: " + coffee.getClass().getName());
        return coffee;
    }
}
