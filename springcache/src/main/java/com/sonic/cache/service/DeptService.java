package com.sonic.cache.service;

import com.sonic.cache.bean.Department;
import com.sonic.cache.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * DeptService
 *
 * @author Sonic
 * @since 2019/6/1
 */
@Service
public class DeptService {

    private Logger logger = LoggerFactory.getLogger(DeptService.class);

    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id) {
        logger.info("Query dept:{}", id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

}
