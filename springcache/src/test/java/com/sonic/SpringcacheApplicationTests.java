package com.sonic;

import com.sonic.cache.bean.Employee;
import com.sonic.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcacheApplicationTests {

    private Logger logger = LoggerFactory.getLogger(SpringcacheApplicationTests.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void contextLoads() {

        Employee emp = employeeMapper.getEmpById(1);
        logger.info("emp: {}", emp);
    }

}
