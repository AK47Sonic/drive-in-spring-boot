package com.sonic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan(basePackages = {"com.sonic.cache.mapper"})
@SpringBootApplication
public class SpringcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcacheApplication.class, args);
    }

}
