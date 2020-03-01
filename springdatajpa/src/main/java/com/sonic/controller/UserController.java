package com.sonic.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author Sonic
 * @since 2019/5/26
 */
@RestController
public class UserController {

//    private Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    HelloService helloService;
//
//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable("id") Integer id){
//        Optional<User> userOptional = userRepository.findById(id);
//        logger.info("user:{}", userOptional.get());
//        return userOptional.get();
//    }
//
//    @GetMapping("/save")
//    public User insertUser(User user) {
//        User userSave = userRepository.save(user);
//        return userSave;
//    }
//
//    @GetMapping("/hello")
//    public String hello(){
//        return helloService.sayHello("hh");
//    }


}
