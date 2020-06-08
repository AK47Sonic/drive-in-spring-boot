package com.sonic.demo.controller;

import com.sonic.demo.domain.User;
import com.sonic.demo.mapper.mysql1.MySQL1Mapper;
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

    private void throwEx(String flag) {
        if (flag.contains("error")) {
            throw new RuntimeException("Business Exception");
        }
    }

    @GetMapping("/message")
    public void sendMsg(@RequestParam String name) {
        log.info("Send msg to create user: {}", name);
        User user = User.builder().userId("2")
                .userName(name)
                .build();
        jmsTemplate.convertAndSend("user:msg:new", user);
    }

    @Transactional
    @JmsListener(destination = "user:msg:new", containerFactory = "msgFactory")
    public void create(User user) {
        log.info("Receive user:{}", user.getUserName());
        mySQL1Mapper.insertUser(user);
        log.info("Save user:{}", user.getUserName());
        jmsTemplate.convertAndSend("user:msg:reply", user);
        throwEx(user.getUserName());
    }

    @JmsListener(destination = "user:msg:reply")
    public void receive(User user) {
        log.info("---------- receive user on reply queue:{} -------------", user);
    }

}
