package com.sonic.driveinspringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TODO
 * Create by Sonic on 2018/12/1
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
//                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println("ConfigurableApplicationContext type: " + context.getClass().getName());
        System.out.println("Environment type: " + context.getEnvironment().getClass().getName());

        context.close();
    }
}
