package com.sonic;

import com.sonic.bean.Coffee;
import com.sonic.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatisCursorItemReaderTest
 *
 * @author Sonic
 * @since 2019/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class MyBatisCursorItemReaderTest {

    @Autowired
    private MyBatisCursorItemReader sonicMyBatisCursorItemReader;

    @Test
    public void testMyBatisCursorItemReader() {

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", 1);
//        //查询的sql所3需要的参数
//        paramsMap.put("oredCriteria",transferExample.getOredCriteria());
//        paramsMap.put("orderByClause",transferExample.getOrderByClause());
        //设置参数
        sonicMyBatisCursorItemReader.setParameterValues(paramsMap);
        //打开
        sonicMyBatisCursorItemReader.open(new ExecutionContext());
        List<Employee> smallChunk = new ArrayList(10);
        Coffee coffee;
        int count = 0;
        try {
            while ((coffee = (Coffee) sonicMyBatisCursorItemReader.read()) != null) {
                // Fetch next 10 employees
//            for(int i = 0; i<10 && iter.hasNext(); i++) {
//                smallChunk.add(iter.next());
//            }
//            doSomethingWithAlreadyFetchedEmployees(smallChunk);

                System.out.println("Coffee: " + coffee);
                ++count;
                System.out.println(count);
                smallChunk.clear();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sonicMyBatisCursorItemReader.close();
        }
    }
}
