package com.sonic.driveinspringboot.configuration;

import org.springframework.context.annotation.Bean;

/**
 * Create by Sonic on 2018/11/25
 */
//@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() { // 方法名即 Bean 名称
        return "Hello,World 2018";
    }

}
