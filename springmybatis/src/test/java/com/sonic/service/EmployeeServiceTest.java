package com.sonic.service;

import com.sonic.StartUpApplication;
import com.sonic.bean.Department;
import com.sonic.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmployeeServiceTest
 *
 * @auther Sonic
 * @since 2018/12/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StartUpApplication.class)
public class EmployeeServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    @Test
    public void testGetEmployeeById1Cache() {
        int id = 1;
        List<Employee> employees = employeeService.getEmployee(id);
        logger.info("employees: " + employees);

//        Employee employee = new Employee(0, "jerry", "jefrry@hotmail.com", "1");
//        Long lineNum = employeeService.addEmployee(employee);
//        logger.info("lineNum: " + lineNum);
//        logger.info("employee: " + employee);

        List<Employee> employees2 = employeeService.getEmployee(id);
        logger.info("employees2: " + employees2);
        logger.info("equals: " + (employees == employees2));
    }

    @Test
    public void testAddEmpsMultiParam() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(0, "QiYa3", "QiYa@hotmailcom", "1",
                new Department(3, "Wire"));
        Employee employee2 = new Employee(0, "Gang3", "Gang@hotmailcom", "1",
                new Department(3, "Wire"));
        employeeList.add(employee1);
        employeeList.add(employee2);
        boolean result = employeeService.addEmpsMultiParam(0, employeeList);
        logger.info("result: " + result);
    }

    @Test
    public void testEmpsInnerMultiParam() {
        Employee employee = new Employee(1, "%Le%", "RockLee@hotmailcom", null);
//        Employee employee = null;
        List<Employee> employees = employeeService.getEmpsInnerMultiParam(0, employee);
        logger.info("employees: " + employees);
    }

    @Test
    public void testEmpsBind() {
        Employee employee = new Employee(1, "Le", "RockLee@hotmailcom", null);
//        Employee employee = null;
        List<Employee> employees = employeeService.getEmpsEmpsBind(employee);
        logger.info("employees: " + employees);
    }

    @Test
    public void testEmpsInnerParam() {
        Employee employee = new Employee(1, "%Le%", "RockLee@hotmailcom", null);
//        Employee employee = null;
        List<Employee> employees = employeeService.getEmpsInnerParam(employee);
        logger.info("employees: " + employees);
    }

    @Test
    public void testBatchInsertEmps() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(0, "Smith", "Smith@hotmailcom", "1",
                new Department(3, "Wire"));
        Employee employee2 = new Employee(0, "Rain", "Rain@hotmailcom", "1",
                new Department(3, "Wire"));
        employeeList.add(employee1);
        employeeList.add(employee2);
        long affectedRowCount = employeeService.addEmpsForeach(employeeList);
        logger.info("affected row count: " + affectedRowCount);
        logger.info("employeeList: " + employeeList);
    }

    @Test
    public void testQueryEmployeeMapperDynamicSQLForeach() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Employee> employeeList = employeeService.getEmployeeMapperDynamicSQLForeach(list);
        logger.info("employees: " + employeeList);
    }

    @Test
    public void testUpdateEmployeeDynamicSQL() {
        Employee employee = new Employee(1, "RockLee", "RockLee@hotmailcom", null);
        long affectedRowCount = employeeService.updateEmployeeDynamicSQL(employee);
        logger.info("affected row count: " + affectedRowCount);
    }

    @Test
    public void testQueryEmployeeMapperDynamicSQL() {
        Employee employee = new Employee(0, "%a%", null, null);
        List<Employee> employeeList = employeeService.getEmployeeMapperDynamicSQL(employee);
        logger.info("employees: " + employeeList);
    }

    /**
     * Lazy loading
     */
    @Test
    public void testQueryEmployeeAndDeptStep() {
        int id = 4;
        Employee employee = employeeService.getEmployeeAndDeptStep(id);
        logger.info("employee: " + employee);
//        logger.info("Employee lastName: " + employee.getLastName());
//        logger.info("Department name: " + employee.getDepartment().getDepartmentName());
    }

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
