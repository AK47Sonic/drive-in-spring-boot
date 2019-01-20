package com.sonic.mapper;

import com.sonic.bean.Employee;

import java.util.List;

/**
 * EmployeeMapperDynamicSQL
 *
 * @auther Sonic
 * @since 2019/1/6
 */
public interface EmployeeMapperDynamicSQL {

    /**
     * Caused by: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named '_lastName' in 'class com.sonic.bean.Employee'
     * where last_name like #{_parameter._lastName} 不能使用，_parameter指得是传入的参数。因此，Employee没有_lastName这个属性
     */
    List<Employee> getEmpsBind(Employee employee);

    boolean addEmpsMultiParam(int id, List<Employee> employeeList);

    List<Employee> getEmpsInnerMultiParam(int id, Employee employee);

    List<Employee> getEmpsInnerParam(Employee employee);

    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionTrim(Employee employee);

    List<Employee> getEmpsByConditionChoose(Employee employee);

    long updateEmp(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    long addEmps(List<Employee> employeeList);

}
