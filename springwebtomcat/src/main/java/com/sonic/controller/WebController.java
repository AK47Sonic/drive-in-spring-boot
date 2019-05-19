package com.sonic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    private Logger logger = LoggerFactory.getLogger(WebController.class);

    //JSP 和 Thymeleaf不兼容 (JSP)
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    // Thymeleaf
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>hello Sonic</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }

    @PostMapping(value = "/content")
//    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public String content(@RequestParam("userName") String userName, @RequestParam("password") String password,
                          Map<String, String> map, HttpSession session) {
        logger.info("password: {}", password);
        if (!StringUtils.isEmpty(userName) && "123456".equals(password)) {
            session.setAttribute("loginUser", userName);
            // 防止F5表单重复提交，重定向到当前项目下main.html
            return "redirect:/main.html";
        } else {
            map.put("msg", "password is not right");
            return "success";
        }
    }

}
