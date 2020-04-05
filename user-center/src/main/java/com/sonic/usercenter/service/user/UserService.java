package com.sonic.usercenter.service.user;

import com.sonic.usercenter.dao.user.UserMapper;
import com.sonic.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author Sonic
 * @since 2020/4/5
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserMapper userMapper;

    public User findById(Integer id) {
       return this.userMapper.selectByPrimaryKey(id);
    }

}
