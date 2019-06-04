package com.sonic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * AsyncAppBootstrap
 *
 * @author Sonic
 * @since 2019/6/4
 */
//开启异步注解
@EnableAsync
// 开启定时任务
@EnableScheduling
@SpringBootApplication
public class AsyncAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AsyncAppBootstrap.class, args);
    }

}
