package com.sonic.service;

import com.sonic.bean.Employee;
import com.sonic.mapper.EmployeeMapper;
import com.sonic.mapper.EmployeeMapperPlus;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * EmployeeService
 *
 * @auther Sonic
 * @since 2018/12/29
 */
@Service("employeeService")
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private EmployeeMapperPlus employeeMapperPlus;

    public Employee getEmployeeAndDeptStep(int id){
        Employee employee = employeeMapperPlus.getEmployeeByIdStep(id);
        return  employee;
    }

    public Employee getEmployeeAndDept(int id){
        Employee employee = employeeMapperPlus.getEmployeeAndDept(id);
        return  employee;
    }

    public Employee getEmployeePlusResultMap(int id){
        Employee employee = employeeMapperPlus.getEmployeeById(id);
        return  employee;
    }

    public Map<Integer, Employee> getEmployeeReturnComplexMap(String lastName){
        Map<Integer, Employee> employeeByIdReturnComplexMap = employeeMapper.getEmployeeReturnComplexMap(lastName);
        return  employeeByIdReturnComplexMap;
    }

    public Map<String, Object> getEmployeeByIdReturnMap(int id){
        Map<String, Object> employeeByIdReturnMap = employeeMapper.getEmployeeByIdReturnMap(id);
        return  employeeByIdReturnMap;
    }

    public List<Employee> getEmployeeByIdAndLastName(int id, String lastName){
        List<Employee> employeeList = employeeMapper.getEmployeeByIdAndLastName(id, lastName);
        return  employeeList;
    }

    public List<Employee> getEmployeeByIdAndLastNameParam(int id, String lastName){
        List<Employee> employeeList = employeeMapper.getEmployeeByIdAndLastNameParam(id, lastName);
        return  employeeList;
    }

    public List<Employee> getEmployeeByIdAndLastNameMap(Map<String, Object> map){
        List<Employee> employeeList = employeeMapper.getEmployeeByIdAndLastNameMap(map);
        return  employeeList;
    }

    public List<Employee> getEmployee(int id){
        List<Employee> employeeList = employeeMapper.getEmployee(id);
        return  employeeList;
    }

    public List<Employee> getEmployeeByTemplate(int id){
        List<Employee> employees = sqlSessionTemplate.<Employee>selectList("com.sonic.mapper.EmployeeMapper.getEmployee", id);
        return  employees;
    }

    public Long addEmployee(Employee employee){
        return employeeMapper.addEmployee(employee);
    }

    public void updateEmployee(Employee employee){
        employeeMapper.updateEmployee(employee);
    }

    public void deleteEmployeeById(int id){
        employeeMapper.deleteEmployeeById(id);
    }

}
