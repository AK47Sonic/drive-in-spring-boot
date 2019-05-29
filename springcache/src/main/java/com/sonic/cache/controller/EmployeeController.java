package com.sonic.cache.controller;

import com.sonic.cache.bean.Employee;
import com.sonic.cache.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 *
 * @author Sonic
 * @since 2019/5/28
 */
@RestController
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmp(id);
        return emp;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/delemp")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        Employee emp = employeeService.getEmpByLastName(lastName);
        return emp;
    }

}
