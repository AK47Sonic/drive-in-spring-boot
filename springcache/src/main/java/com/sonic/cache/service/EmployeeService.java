package com.sonic.cache.service;

import com.sonic.cache.bean.Employee;
import com.sonic.cache.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * EmployeeService
 *
 * @author Sonic
 * @since 2019/5/28
 */
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate redisTemplate;

    public Employee getEmpManual(Integer id) {
        logger.info("Query emp: {}", id);
        Employee emp = employeeMapper.getEmpById(id);
        redisTemplate.opsForValue().set(id, emp);
        return emp;
    }



    //    @Cacheable(cacheNames = {"emp", "temp"}, condition = "#id>0", unless = "#result == null")
    @Cacheable(cacheNames = {"emp"})
//    @Cacheable(cacheNames = {"emp"}, key = "#root.methodName+'['+#root.args[0]+']'")
//    @Cacheable(cacheNames = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#id>1", unless = "#a0==2")
    public Employee getEmp(Integer id) {
        logger.info("Query emp: {}", id);
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * 1. 先调用目标方法
     * 2. 将目标方法的结果缓存起来
     * 坑：注意緩存对象的key，这里是employee，而不是empid
     * {@code @Cacheable} 不能用#result, 因为在调用方法前，会先用key去查询缓存，这时候的result还没有生成
     */
//    @CachePut(value = "emp", key = "#emoloyee.id")
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        logger.info("updateEmp: {}", employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /*
    {@code @CacheEvict} 缓存清除
    allEntries 删除所有key的缓存
    beforeInvocation 在方法之前清缓存，假如方法出错，缓存已经被清空
     */
//    @CacheEvict(value = "emp", key = "#id")
    @CacheEvict(value = "emp", key = "#id", allEntries = true, beforeInvocation = true)
    public void deleteEmp(Integer id) {
        logger.info("deleteEmp: {}", id);
    }

    // 为什么任然调用数据库？ 因为@CachePut 一定要执行方法
//    @Caching(
//            cacheable = {@Cacheable(value = "emp", key = "#lastName")},
//            put = {@CachePut(value = "emp", key = "#result.id"),
//                    @CachePut(value = "emp", key = "#result.email"),
//            }
//    )
    @Caching(
            cacheable = {@Cacheable(key = "#lastName")},
            put = {@CachePut(key = "#result.id"),
                    @CachePut(key = "#result.email"),
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

}
