package com.sonic.testcase;

import com.sonic.StartUpApplication;
import com.sonic.bean.Employee;
import com.sonic.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmployeeTest
 *
 * @auther Sonic
 * @since 2018/12/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StartUpApplication.class)
public class EmployeeTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testQueryEmployeeAndDept() {
        int id = 10;
        Employee employee = employeeService.getEmployeeAndDept(id);
        logger.info("employee: " + employee);
    }

    @Test
    public void testQueryEmployeeResultMap() {
        int id = 10;
        Employee employee = employeeService.getEmployeePlusResultMap(id);
        logger.info("employee: " + employee);
    }

    @Test
    public void testQueryEmployeeReturnComplexMap() {
        Map<Integer, Employee> employeeReturnComplexMap = employeeService.getEmployeeReturnComplexMap("%e%");
        logger.info("employeeReturnComplexMap: " + employeeReturnComplexMap);
    }

    @Test
    public void testQueryEmployeeByIdReturnMap() {
        int id = 10;
        Map<String, Object> employeeByIdReturnMap = employeeService.getEmployeeByIdReturnMap(id);
        logger.info("employeeByIdReturnMap: " + employeeByIdReturnMap);
    }

    @Test
    public void testQueryEmployeeByIdAndLastName() {
        int id = 10;
        String lastName = "Allen";
        List<Employee> employees = employeeService.getEmployeeByIdAndLastName(id, lastName);
        logger.info("employees: " + employees);
    }

    @Test
    public void testQueryEmployeeByIdAndLastNameParam() {
        int id = 10;
        String lastName = "Allen";
        List<Employee> employees = employeeService.getEmployeeByIdAndLastNameParam(id, lastName);
        logger.info("employees: " + employees);
    }

    @Test
    public void testQueryEmployeeByIdAndLastNameMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 10);
        map.put("lastName", "Allen");
        map.put("tableName", "tbl_employee");
        List<Employee> employees = employeeService.getEmployeeByIdAndLastNameMap(map);
        logger.info("employees: " + employees);
    }

    @Test
    public void testQueryEmployee() {
        int id = 1;
        List<Employee> employees = employeeService.getEmployee(id);
        logger.info("employees: " + employees);
    }

    @Test
    public void testQueryEmployeeByTemplate() {
        int id = 1;
        List<Employee> employees = employeeService.getEmployeeByTemplate(id);
        logger.info("employees: " + employees);
    }

    @Test
    public void addEmployee() {
        Employee employee = new Employee(0, "jerry", "jefrry@hotmail.com", "1");
        Long lineNum = employeeService.addEmployee(employee);
        logger.info("lineNum: " + lineNum);
        logger.info("employee: " + employee);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee(15, "Bill", "Bill@hotmail.com", "1");
        employeeService.updateEmployee(employee);
    }

    @Test
    public void deleteEmployeeById() {
        int id = 15;
        employeeService.deleteEmployeeById(id);
    }

}
