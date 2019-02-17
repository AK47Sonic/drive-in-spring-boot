package com.sonic.bootstrap;

import com.sonic.config.ExtraTypeFilter;
import com.sonic.extrascanpackage.Building;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

/**
 * ComponentScansBootstrap
 *
 * @author Sonic
 * @since 2019/2/17
 */

@ComponentScans(
value = {
//        @ComponentScan(value = "com.sonic.controller"),
        @ComponentScan(value = "com.sonic.extrascanpackage", includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {ExtraTypeFilter.class})}, useDefaultFilters = false)
}
)
@SpringBootApplication(scanBasePackages = {"com.sonic.controller","com.sonic.service","com.sonic.dao"})
public class ComponentScansBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ComponentScansBootstrap.class, args);
//        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(applicationContext.getBean("building", Building.class));

    }
}
