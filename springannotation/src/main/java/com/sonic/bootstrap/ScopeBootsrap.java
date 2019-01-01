package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Scope @Lazy 使用
 *
 * @auther Sonic
 * @since 2018/12/15
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config"})
public class ScopeBootsrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ScopeBootsrap.class, args);
//        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("----------Get bean--------------");
        Object person1 = ac.getBean("person");
        Object person2 = ac.getBean("person");
        System.out.println(person1 == person2);


        ac.close();
    }
}
