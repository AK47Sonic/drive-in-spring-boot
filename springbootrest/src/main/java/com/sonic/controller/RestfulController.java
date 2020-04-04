package com.sonic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestfulController
 *
 * @author Sonic
 * @since 2020/4/3
 */
@RestController
public class RestfulController {

    @RequestMapping("/hello")
    public String getHello(@RequestParam String text) {
        return RequestMethod.GET + " " + text;
    }

    @RequestMapping(value = "/hellopost", method = RequestMethod.POST)
    public String getHelloPost(@RequestParam String text) {
        return RequestMethod.POST + " " + text;
    }

}
