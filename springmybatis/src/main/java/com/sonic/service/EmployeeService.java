package com.sonic.service;

import com.sonic.bean.Employee;
import com.sonic.mapper.EmployeeMapper;
import com.sonic.mapper.EmployeeMapperDynamicSQL;
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
    private EmployeeMapperDynamicSQL employeeMapperDynamicSQL;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private EmployeeMapperPlus employeeMapperPlus;

    public boolean addEmpsMultiParam(int id, List<Employee> employeeList) {
        boolean result = employeeMapperDynamicSQL.addEmpsMultiParam(id, employeeList);
        return result;
    }


    public List<Employee> getEmpsInnerMultiParam(int id, Employee employee) {
        List<Employee> employees = employeeMapperDynamicSQL.getEmpsInnerMultiParam(id, employee);
        return employees;
    }

    public List<Employee> getEmpsInnerParam(Employee employee) {
        List<Employee> employees = employeeMapperDynamicSQL.getEmpsInnerParam(employee);
        return employees;
    }

    public long addEmpsForeach(List<Employee> employeeList) {
        long affectedRows = employeeMapperDynamicSQL.addEmps(employeeList);
        return affectedRows;
    }

    public List<Employee> getEmployeeMapperDynamicSQLForeach(List<Integer> ids) {
        List<Employee> employees = employeeMapperDynamicSQL.getEmpsByConditionForeach(ids);
        return employees;
    }

    public long updateEmployeeDynamicSQL(Employee employee) {
        long affectedRowCount = employeeMapperDynamicSQL.updateEmp(employee);
        return affectedRowCount;
    }

    public List<Employee> getEmployeeMapperDynamicSQL(Employee employee) {
//        List<Employee> employees = employeeMapperDynamicSQL.getEmpsByConditionIf(employee);
//        List<Employee> employees = employeeMapperDynamicSQL.getEmpsByConditionTrim(employee);
        List<Employee> employees = employeeMapperDynamicSQL.getEmpsByConditionChoose(employee);
        return employees;
    }


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
