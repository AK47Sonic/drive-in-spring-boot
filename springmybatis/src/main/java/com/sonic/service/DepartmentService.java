package com.sonic.service;

import com.sonic.bean.Department;
import com.sonic.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DepartmentService
 *
 * @auther Sonic
 * @since 2019/1/5
 */
@Service("departmentService")
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDepartmentById(int id){
        Department department = departmentMapper.getDeptByIdPlus(id);
        return department;
    }

    public Department getDeptByIdStep(int depId){
        Department department = departmentMapper.getDeptByIdStep(depId);
        return department;
    }


}
