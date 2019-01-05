package com.sonic.service;

import com.sonic.StartUpApplication;
import com.sonic.bean.Department;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DepartmentService Tester.
 *
 * @author Sonic
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {StartUpApplication.class})
public class DepartmentServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceTest.class);

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getDepartmentById(int id)
     */
    @Test
    public void testGetDepartmentById() {
        int id = 1;
        Department department = departmentService.getDepartmentById(id);
        logger.info("department: " + department);
    }

    @Test
    public void testGetDeptByIdStep() {
        int id = 1;
        Department department = departmentService.getDeptByIdStep(id);
//        logger.info("department: " + department);
        logger.info("department Id: " + department.getId());
    }

} 
