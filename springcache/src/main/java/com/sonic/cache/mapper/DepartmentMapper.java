package com.sonic.cache.mapper;

import com.sonic.cache.bean.Department;
import org.apache.ibatis.annotations.Select;

/**
 * DepartmentMapper
 *
 * @author Sonic
 * @since 2019/5/28
 */
//@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);
}
