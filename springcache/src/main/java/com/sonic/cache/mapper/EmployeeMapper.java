package com.sonic.cache.mapper;

import com.sonic.cache.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * EmployeeMapper
 *
 * @author Sonic
 * @since 2019/5/28
 */
//@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName}, email=#{email},gender=#{gender},did=#{did} where id=#{id}")
    void updateEmp(Employee employee);

    @Delete("delete from employee where id=#{id}")
    void deleteEmp(Integer id);

    @Insert("insert into employee(lastName,email,gender,did) values(#{lastName}，#{email}，#{gender}，did=#{did})")
    void insertEmployee(Employee employee);

    @Select("select * from employee where lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);

}
