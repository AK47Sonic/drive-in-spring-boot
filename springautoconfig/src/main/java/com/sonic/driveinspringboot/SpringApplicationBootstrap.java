package com.sonic.driveinspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by Sonic on 2018/11/27
 */

public class SpringApplicationBootstrap {
    public static void main(String[] args) {
//        SpringApplication.run(ApplicationConfiguration.class, args);

        Set<String> sources = new HashSet<>();
        sources.add(ApplicationConfiguration.class.getName());
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(sources);
        ConfigurableApplicationContext ac = springApplication.run(args);
        ApplicationConfiguration bean = ac.getBean(ApplicationConfiguration.class);
        System.out.println(bean);

    }
    @SpringBootApplication
    public static class ApplicationConfiguration {

    }
}
