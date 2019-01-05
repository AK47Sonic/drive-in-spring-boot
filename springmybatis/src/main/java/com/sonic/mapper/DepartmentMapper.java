package com.sonic.mapper;

import com.sonic.bean.Department;

/**
 * DepartmentMapper
 *
 * @auther Sonic
 * @since 2019/1/5
 */
public interface DepartmentMapper {

    Department getDeptById(int id);

    Department getDeptByIdPlus(int id);

    Department getDeptByIdStep(int id);
}
