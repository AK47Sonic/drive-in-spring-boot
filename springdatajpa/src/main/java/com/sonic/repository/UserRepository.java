package com.sonic.repository;

import com.sonic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Sonic
 * @since 2019/5/26
 */

// 继承JpaRepository完成对数据库的操作
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



}
