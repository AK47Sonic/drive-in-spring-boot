package com.sonic.bootstrap;

import com.sonic.bean.Blue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SigletonBootstrap
 *
 * @auther Sonic
 * @since 2018/12/22
 */
@SpringBootApplication(scanBasePackages = "com.sonic.singletonconfig")
public class SigletonBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SigletonBootstrap.class, args);
        Blue blueBean = applicationContext.getBean(Blue.class);
        System.out.println(blueBean);
        System.out.println("blue1 : " + applicationContext.getBean("blue1",Blue.class));
        System.out.println("blue2 : " + applicationContext.getBean("blue2",Blue.class));
        System.out.println("blue3 : " + applicationContext.getBean("blue3",Blue.class));

    }

}
