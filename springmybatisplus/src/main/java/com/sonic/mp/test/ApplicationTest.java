package com.sonic.mp.test;

import com.sonic.mp.StartUpApplication;
import com.sonic.mp.beans.User;
import com.sonic.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        user.setName("Sonic");
        user.setAge(10);
        user.setEmail("qq@qq.com");
        int r = userMapper.insert(user);
        System.out.println(r);
        System.out.println("user: " + user);
    }

}
