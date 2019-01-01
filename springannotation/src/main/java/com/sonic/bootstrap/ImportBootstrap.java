package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @Import 使用
 * @auther Sonic
 * @since 2018/12/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config"})
public class ImportBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ImportBootstrap.class, args);
        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);

        ac.close();
    }
}
