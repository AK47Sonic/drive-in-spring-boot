package com.sonic.mapper;

import com.sonic.bean.Employee;

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
}
