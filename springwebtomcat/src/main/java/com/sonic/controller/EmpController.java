package com.sonic.controller;

import com.sonic.bean.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * EmpController
 *
 * @author Sonic
 * @since 2019/5/19
 */
@Controller
public class EmpController {

    private Logger logger = LoggerFactory.getLogger(EmpController.class);

    @GetMapping("/emps")
    public String list(Model model) {

        List<Employee> employeeList = getEmps();
        model.addAttribute("emps", employeeList);

        //    - ThymeleafProperties#DEFAULT_PREFIX
        //    - ThymeleafProperties#DEFAULT_SUFFIX
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage() {

        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){

        logger.info("employee: {}", employee);

        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址

        return "redirect:/emps";
//        return "forward:/emps";
    }

    private List<Employee> getEmps() {

        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Sky");

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Blue");

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setName("Yellow");

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        return employeeList;
    }

}
