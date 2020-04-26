package com.sonic.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author Sonic
 * @since 2020/4/4
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

}