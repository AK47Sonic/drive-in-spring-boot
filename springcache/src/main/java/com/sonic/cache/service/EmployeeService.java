package com.sonic.cache.service;

import com.sonic.cache.bean.Employee;
import com.sonic.cache.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * EmployeeService
 *
 * @author Sonic
 * @since 2019/5/28
 */
@Service
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeMapper employeeMapper;

//    @Cacheable(cacheNames = {"emp", "temp"}, condition = "#id>0", unless = "#result == null")
//    @Cacheable(cacheNames = {"emp"})
//    @Cacheable(cacheNames = {"emp"}, key = "#root.methodName+'['+#root.args[0]+']'")
    @Cacheable(cacheNames = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#id>1", unless = "#a0==2")
    public Employee getEmp(Integer id) {
        logger.info("Query emp: {}", id);
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }
}
