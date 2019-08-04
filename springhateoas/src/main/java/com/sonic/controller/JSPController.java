package com.sonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * JSPController
 *
 * @author Sonic
 * @since 2019/8/4
 */
@Controller
public class JSPController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("message", "Sky");
        return "index";
    }

}
