package com.sonic;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ApplicationBootstrap
 *
 * @author Sonic
 * @since 2019/4/20
 */
@SpringBootApplication
public class ApplicationBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationBootstrap.class).run(args);
    }
}
