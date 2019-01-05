package com.sonic.mapper;

import com.sonic.bean.Employee;

import java.util.List;

/**
 * EmployeeMapperPlus
 *
 * @auther Sonic
 * @since 2019/1/2
 */
public interface EmployeeMapperPlus {

    Employee getEmployeeById(int id);

    Employee getEmployeeAndDept(int id);

    Employee getEmployeeByIdStep(int id);

    List<Employee> getEmployeesByDeptId(int deptId);
}
