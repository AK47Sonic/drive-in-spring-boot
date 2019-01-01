package com.sonic.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Dog
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext ac;

    public Dog() {
        System.out.println("Dog constructor..");
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("Dog PostConstruct..");
    }

    //容器移除对象之前
    @PreDestroy
    public void destroy(){
        System.out.println("Dog PreDestroy..");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }
}
