package com.sonic.cache.controller;

import com.sonic.cache.bean.Department;
import com.sonic.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeptController
 *
 * @author Sonic
 * @since 2019/6/1
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {

        return deptService.getDeptById(id);
    }

}
