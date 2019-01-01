package com.sonic.bootstrap;

import com.sonic.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Value
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config", "com.sonic.bean"})
public class PropertyValueBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(PropertyValueBootstrap.class, args);
//        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);
        Person sky = ac.getBean("sky", Person.class);
        String property = ac.getEnvironment().getProperty("person.nickName");
        System.out.println("property : " + property);
        System.out.println(sky);
        ac.close();
    }
}
