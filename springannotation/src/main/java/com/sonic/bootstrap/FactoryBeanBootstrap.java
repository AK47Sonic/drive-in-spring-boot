package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * FactoryBean 使用
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config"})
public class FactoryBeanBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(FactoryBeanBootstrap.class, args);

        Object colorBean = ac.getBean("colorFactoryBean");
        Object colorBean2 = ac.getBean("colorFactoryBean");
        System.out.println(colorBean == colorBean2);
        System.out.println("colorBean class : " + colorBean.getClass().getName());
        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);

        Object colorFactoryBean = ac.getBean("&colorFactoryBean");
        System.out.println("colorFactoryBean class : " + colorFactoryBean.getClass().getName());

        ac.close();
    }
}
