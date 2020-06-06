package com.sonic.demo.controller;

import com.sonic.demo.domain.User;
import com.sonic.demo.mapper.mysql1.MySQL1Mapper;
import com.sonic.demo.mapper.mysql2.MySQL2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author Sonic
 * @since 2020/4/4
 */
@Slf4j
@RestController
public class TestController {
//    @GetMapping("/test")
//    public String test() throws InterruptedException {
//        Thread.sleep(5000);
//        System.out.println("xxx");
//        return "test";
//    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired(required = false)
    private MySQL1Mapper mySQL1Mapper;

    @Autowired(required = false)
    private MySQL2Mapper mySQL2Mapper;

    @Transactional(transactionManager = "xaTransactionManager")
    @GetMapping("/save2DB")
    public void save2DB(@RequestParam String flag) {
        log.info("----------save2DB---------");
        User user = User.builder().userId("1")
                .userName("Sonic")
                .build();
        mySQL1Mapper.insertUser(user);
        mySQL2Mapper.insertUser(user);

        throwEx(flag);

    }

    private void throwEx(String flag) {
        if ("1".equals(flag)) {
            throw new RuntimeException("Business Exception");
        }
    }

    @Transactional
    @JmsListener(destination = "user:msg:new")
    public void create(String name) {
        log.info("create user:{}", name);
        User user = User.builder().userId("2")
                .userName("Aon" + name)
                .build();
        mySQL1Mapper.insertUser(user);
        jmsTemplate.convertAndSend("user:msg:reply", user.getUserName());
        throwEx("1");
    }

    @GetMapping("/message")
    public void sendMsg(@RequestParam String name) {
        log.info("send msg to create user: {}", name);
        jmsTemplate.convertAndSend("user:msg:new", name);
    }

    @JmsListener(destination = "user:msg:reply")
    public void receive(String msg) {
        log.info("---------- receive msg:{} -------------", msg);
    }

}
