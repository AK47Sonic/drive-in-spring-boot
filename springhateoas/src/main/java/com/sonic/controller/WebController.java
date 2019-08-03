package com.sonic.controller;

import com.sonic.bean.User;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebController
 *
 * @author Sonic
 * @since 2019/8/3
 */
@RestController
public class WebController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Sky");

        user.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(WebController.class).getUser())
                .withSelfRel());

        return user;
    }
}
