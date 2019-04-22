package com.sonic.controller;

import com.sonic.bean.Coffee;
import com.sonic.service.RestService;
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
}
