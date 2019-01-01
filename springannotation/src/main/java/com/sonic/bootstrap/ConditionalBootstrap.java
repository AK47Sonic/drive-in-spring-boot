package com.sonic.bootstrap;

import com.sonic.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @Conditional 使用
 *
 * @auther Sonic
 * @since 2018/12/15
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.config"})
public class ConditionalBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ScopeBootsrap.class, args);
        Arrays.stream(ac.getBeanNamesForType(Person.class)).forEach(System.out::println);

//        ConfigurableEnvironment environment = ac.getEnvironment();
//        String os = environment.getProperty("os.name");
//        System.out.println("OS: " + os);
//
//        Map<String, Person> personMap = ac.getBeansOfType(Person.class);
//        System.out.println(personMap);

        ac.close();

    }
}
