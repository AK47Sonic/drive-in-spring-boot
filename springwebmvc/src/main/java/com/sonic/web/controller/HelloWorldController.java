package com.sonic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 *
 * @auther Sonic
 * @since 2018/12/2
 */
@Controller
public class HelloWorldController {
    @RequestMapping("")
    public String index(
            @RequestParam int value,
//            @RequestHeader("Accept-Language") String acceptLanguage,
//                        @CookieValue("JSESSIONID") String jsessionId,
                        Model model) {
//        model.addAttribute("acceptLanguage", acceptLanguage);
//        model.addAttribute("jsessionId", jsessionId);
//        model.addAttribute("message", "HelloWorld");
        System.out.println("aa");
        return "index";
    }


}