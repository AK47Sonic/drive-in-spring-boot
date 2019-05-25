package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * WebAppBootstrap
 *
 * @author Sonic
 * @since 2019/5/25
 */
@SpringBootApplication(scanBasePackages = {"com.sonic"})
public class WebAppBootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebAppBootstrap.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebAppBootstrap.class, args);
    }
}
