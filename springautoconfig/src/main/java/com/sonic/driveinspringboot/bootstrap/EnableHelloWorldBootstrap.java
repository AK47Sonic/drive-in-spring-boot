package com.sonic.driveinspringboot.bootstrap;

import com.sonic.driveinspringboot.annotation.EnableHelloWorld;
import com.sonic.driveinspringboot.configuration.HelloWorldConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Create by Sonic on 2018/11/25
 */
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // helloWorld Bean 是否存在
        String helloWorld =
                context.getBean("helloWorld", String.class);
        System.out.println("helloWorld Bean : " + helloWorld);

//        HelloWorldImportSelector hwis = context.getBean(HelloWorldImportSelector.class);
//        System.out.println("hwis : " + hwis);

        HelloWorldConfiguration hwc = context.getBean(HelloWorldConfiguration.class);
        System.out.println("hwc : " + hwc.toString());
//        System.out.println("class name : " + HelloWorldConfiguration.class.getName());

        // 关闭上下文
        context.close();
    }
}