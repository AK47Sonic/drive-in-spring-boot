package com.sonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WebAppController
 *
 * @author Sonic
 * @since 2019/5/25
 */
@Controller
public class WebAppController {

    @GetMapping("/abc")
    public String abc(){
        return "abc";
    }
}
