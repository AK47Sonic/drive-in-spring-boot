package com.sonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * controller
 *
 * @author Sonic
 * @since 2019/5/9
 */
@Controller
public class WebController {

    //JSP 和 Thymeleaf不兼容 (JSP)
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    // Thymeleaf
    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "<h1>hello Sonic</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

}
