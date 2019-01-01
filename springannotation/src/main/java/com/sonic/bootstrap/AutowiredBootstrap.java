package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sonic.bean", "com.sonic.service", "com.sonic.dao", "com.sonic.controller", "com.sonic.configAutowired"})
public class AutowiredBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(AutowiredBootstrap.class, args);
//        BookService bookService = ac.getBean(BookService.class);
//        bookService.print();
        /**
         * Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException:
         * No qualifying bean of type 'com.sonic.dao.BookDao'
         * available: expected single matching bean but found 2: bookDao,bookDao2
         */
//        BookDao bookDao = ac.getBean(BookDao.class);
//        System.out.println(bookDao);
        for (String s : ac.getBeanDefinitionNames()) {
            System.out.println(s);
        }


//        Boss boss = ac.getBean(Boss.class);
//        System.out.println(boss);
//        Car car = ac.getBean(Car.class);
//        System.out.println(car);
//        Color color = ac.getBean(Color.class);
//        System.out.println(color);
        ac.close();
    }
}
