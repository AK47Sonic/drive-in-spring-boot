package com.sonic;

import com.sonic.cache.bean.Employee;
import com.sonic.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcacheApplicationTests {

    private Logger logger = LoggerFactory.getLogger(SpringcacheApplicationTests.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;


    @Test
    public void test01(){
//        stringRedisTemplate.opsForValue().append("msg", "hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        logger.info("msg: {}", msg);

        stringRedisTemplate.opsForList().leftPush("myList", "1");
        stringRedisTemplate.opsForList().leftPush("myList", "2");
    }

    @Test
    public void test02(){
        Employee emp = employeeMapper.getEmpById(1);
        // 默认使用jdk序列化保存
//        redisTemplate.opsForValue().set("emp-01", emp);

        empRedisTemplate.opsForValue().set("emp-01", emp);
    }


    @Test
    public void contextLoads() {

        Employee emp = employeeMapper.getEmpById(1);
        logger.info("emp: {}", emp);
    }

}
