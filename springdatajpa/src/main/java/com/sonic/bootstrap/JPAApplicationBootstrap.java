package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPAApplicationBootstrap
 *
 * @author Sonic
 * @since 2019/5/26
 */
@SpringBootApplication(scanBasePackages = {"com.sonic"})
@EnableJpaRepositories("com.sonic.repository")
@EntityScan(basePackages = "com.sonic.dto")
public class JPAApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(JPAApplicationBootstrap.class, args);
    }
}
