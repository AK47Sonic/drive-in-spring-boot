package com.sonic.controller;

import com.sonic.entity.User;
import com.sonic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * UserController
 *
 * @author Sonic
 * @since 2019/5/26
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        logger.info("user:{}", userOptional.get());
        return userOptional.get();
    }

    @GetMapping("/save")
    public User insertUser(User user) {
        User userSave = userRepository.save(user);
        return userSave;
    }


}
