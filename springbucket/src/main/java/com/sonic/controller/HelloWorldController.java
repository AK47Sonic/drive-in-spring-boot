package com.sonic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author Sonic
 * @since 2019/4/20
 */
@RestController
public class HelloWorldController {

    @GetMapping("/get")
    public String getHelloWorld(){
        return "Hello World";
    }

}
