package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TODO
 *
 * @auther Sonic
 * @since 2018/12/23
 */
@SpringBootApplication(scanBasePackages = "com.sonic.ext")
public class BeanFactoryBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeanFactoryBootstrap.class, args);
        //发布事件
        applicationContext.publishEvent(new ApplicationEvent(new String("My published event")) {

        });
    }
}
