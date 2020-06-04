package com.sonic.demo.mapper.mysql2;

import com.sonic.demo.domain.User;

/**
 * MySQL2Mapper
 *
 * @author Sonic
 * @since 2020/6/4
 */
public interface MySQL2Mapper {
    void insertUser(User user);
}
