package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * WebApplicationBootstrap
 *
 * @author Sonic
 * @since 2019/5/9
 */
@SpringBootApplication(scanBasePackages = {"com.sonic"})
public class WebApplicationBootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplicationBootstrap.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplicationBootstrap.class, args);
    }
}
