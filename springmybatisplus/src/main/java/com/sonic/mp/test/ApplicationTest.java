package com.sonic.mp.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sonic.mp.StartUpApplication;
import com.sonic.mp.beans.User;
import com.sonic.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * ApplicationTest
 *
 * @author Sonic
 * @since 2020/3/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
public class ApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("BBB");
        user.setAge(10);
        user.setEmail("qq@qq.com");
//        user.setId(10L);
        int r = userMapper.insert(user);
        System.out.println(r);
        System.out.println("user: " + user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("AK");
        user.setId(1234758372583559173L);

        int r = userMapper.updateById(user);
        System.out.println(r);
        System.out.println("user: " + user);
    }

    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1L);
        user.setName("MMMM1225");
//        user.setAge(12);

        userMapper.updateById(user);

    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    /**
     * 分页
     */
    @Test
    public void testPage() {
        Page<User> objectPage = new Page<>(2, 2);
        IPage<User> userIPage = userMapper.selectPage(objectPage, null);
        userIPage.getRecords().forEach(System.out::println);
        System.out.println(userIPage.getTotal());
        System.out.println(userIPage.getPages());
    }

    @Test
    public void testSelectMapsPage() {
//        Page<User> page = new Page<>();
//        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        System.out.println(System.getProperty("user.dir"));
    }

    /**
     * 逻辑删除
     */
    @Test
    public void testDelete() {
        int i = userMapper.deleteById(3L);
        System.out.println(i);
    }

    @Test
    public void testWrapperSelect() {
        Page<User> page = new Page<>(2, 2);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 10, 50).eq("deleted", 0);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPage.getRecords());
    }

    @Test
    public void testAndWrapperSelect() {
        Page<User> page = new Page<>(2, 2);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", 0)
                .and(true, i -> i.between("age", 10, 50).eq("deleted", 0));
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPage.getRecords());
    }

}
