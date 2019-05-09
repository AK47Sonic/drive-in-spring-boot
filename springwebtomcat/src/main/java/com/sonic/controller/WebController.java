package com.sonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller
 *
 * @author Sonic
 * @since 2019/5/9
 */
@Controller
public class WebController {

    @RequestMapping("/helloJsp")
    public String hello(){
        return "hello";
    }

}
