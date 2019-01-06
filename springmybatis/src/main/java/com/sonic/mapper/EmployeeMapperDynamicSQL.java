package com.sonic.mapper;

import com.sonic.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmployeeMapperDynamicSQL
 *
 * @auther Sonic
 * @since 2019/1/6
 */
public interface EmployeeMapperDynamicSQL {

    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionTrim(Employee employee);

    List<Employee> getEmpsByConditionChoose(Employee employee);

    long updateEmp(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    long addEmps(@Param("employeeList") List<Employee> employeeList);

}
