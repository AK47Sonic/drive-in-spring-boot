package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Life Cycle
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config", "com.sonic.bean"})
public class LifeCycleBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(LifeCycleBootstrap.class, args);
        //多实例Bean不会被IOC容器销毁，需要自己管理
//        ac.getBean(Car.class);
        ac.close();
    }
}
