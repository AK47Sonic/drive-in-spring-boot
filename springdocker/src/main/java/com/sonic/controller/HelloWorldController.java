package com.sonic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author Sonic
 * @since 2019/5/2
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String getHelloWorld() {
        return "Hello World";
    }
}
