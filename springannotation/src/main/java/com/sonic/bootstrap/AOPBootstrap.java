package com.sonic.bootstrap;

import com.sonic.aop.MathCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * AOPBootstrap
 *
 * @auther Sonic
 * @since 2018/12/22
 */
@SpringBootApplication(scanBasePackages = "com.sonic.aopconfig")
public class AOPBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AOPBootstrap.class, args);
//        LogAspects la = applicationContext.getBean(LogAspects.class);
//        System.out.println(la);
        MathCalculator mc = applicationContext.getBean(MathCalculator.class);
        mc.div(10, 2);
        System.out.println(mc);
    }

}
