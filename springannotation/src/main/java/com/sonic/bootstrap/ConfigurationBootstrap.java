package com.sonic.bootstrap;

import com.sonic.bean.Person;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Configuration @Bean @ComponentScan使用
 * @auther Sonic
 * @since 2018/12/12
 */
@SpringBootApplication
//Filter[] excludeFilters
@ComponentScans(value = {
        @ComponentScan(
                basePackages = {"com.sonic.bean", "com.sonic.config", "com.sonic.service", "com.sonic.controller", "com.sonic.dao"},
//        excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
//        },
                includeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
// ,
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
//                        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
                },
                useDefaultFilters = false //默认的过滤是什么都不过滤，必须设置为false，才能使得includeFilters生效
        )
        , // 第二个@ComponentScan是追加之前的效果
        @ComponentScan(
                basePackages = {"com.sonic.bean", "com.sonic.config", "com.sonic.service", "com.sonic.controller", "com.sonic.dao"},
                excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})},
                useDefaultFilters = true

        )
})
public class ConfigurationBootstrap {
    public static void main(String[] args) {
        //AnnotationConfigApplicationContext
        ConfigurableApplicationContext ac = new SpringApplicationBuilder(ConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
//        Person person = ac.getBean("person02", Person.class);
//        System.out.println(person);
//
        String[] beanNamesForType = ac.getBeanNamesForType(Person.class);
        for (String beanName : beanNamesForType) {
            System.out.println(beanName);
        }
        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);

        ac.close();
    }

}
