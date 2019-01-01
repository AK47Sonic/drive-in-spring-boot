package com.sonic.testcase;

import com.sonic.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ReflectTest
 *
 * @auther Sonic
 * @since 2018/12/31
 */
@RunWith(SpringRunner.class)
public class ReflectTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectTest.class);
    @Test
    public void testReflect(){
        Class<EmployeeService> employeeServiceClass = EmployeeService.class;
        try {
            Method method = employeeServiceClass.getMethod("getEmployeeByIdAndLastNameMap", Map.class);

//            Parameter[] parameters = method.getParameters();
//            logger.info("parameter: " + parameters);
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            logger.info("parameterAnnotations: " + parameterAnnotations);

//            Employee[][] e = new Employee[1][0];
////            e[0]= new Employee[1];
//            e[0][0]= new Employee();
//            System.out.println(e);

//            String[] strings = new String[0];
//            strings[0] = "1";

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
