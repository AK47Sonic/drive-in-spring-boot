package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootDataJDBCBootstrap
 *
 * @author Sonic
 * @since 2019/5/26
 */
@SpringBootApplication(scanBasePackages = {"com.sonic"})
public class SpringBootDataJDBCBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJDBCBootstrap.class, args);
    }
}
