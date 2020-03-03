package com.sonic.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * StartUpApplication
 *
 * @author Sonic
 * @since 2020/3/3
 */
@SpringBootApplication
@MapperScan("com.sonic.mp.mapper")
//@ComponentScan(basePackages = "com.sonic.mp")
public class StartUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartUpApplication.class, args);
    }
}