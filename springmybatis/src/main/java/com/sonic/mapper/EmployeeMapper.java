package com.sonic.mapper;

import com.sonic.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * EmployeeMapper
 *
 * @auther Sonic
 * @since 2018/12/29
 */
public interface EmployeeMapper {

    @MapKey("id")
    Map<Integer, Employee> getEmployeeReturnComplexMap(String lastName);

    Map<String, Object> getEmployeeByIdReturnMap(int id);

    List<Employee> getEmployeeByIdAndLastNameMap(Map<String,Object> map);

    List<Employee> getEmployeeByIdAndLastName(int id, String lastName);

    List<Employee> getEmployeeByIdAndLastNameParam(@Param("id") int id, @Param("lastName") String lastName);

    List<Employee> getEmployee(int id);

    Long addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(int id);

    List<Employee> getEmps();

}
