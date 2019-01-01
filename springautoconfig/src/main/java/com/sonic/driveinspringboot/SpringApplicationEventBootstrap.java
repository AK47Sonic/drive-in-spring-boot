package com.sonic.driveinspringboot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 * Create by Sonic on 2018/12/1
 */
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addApplicationListener(event -> {
            System.out.println("监听到事件: " + event);
        });
        //启动上下文
        System.out.println("----------refresh-------------");
        context.refresh();

        context.publishEvent("Hello World");
        context.publishEvent("2018");
        context.publishEvent(new ApplicationEvent("Sonic"){});

        //关闭上下文
        System.out.println("----------close-------------");
        context.close();

    }
}
