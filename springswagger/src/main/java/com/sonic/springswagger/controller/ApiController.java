package com.sonic.springswagger.controller;

import com.sonic.springswagger.domain.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * ApiController
 *
 * @author Sonic
 * @since 2020/4/28
 */
@Api("API controller")
@RestController
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    /**
     * 返回值中有对象则会显示
     */
    @ApiOperation("user api")
    @PostMapping("/user")
    public User getUser( @ApiParam("user entity") @RequestBody User user) {
        return user;
    }

}
