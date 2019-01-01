package com.sonic.bean;

import org.springframework.stereotype.Component;

/**
 * Car
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@Component
public class Car {

    public Car(){
        System.out.println("Car constructor..");
    }

    public void init(){
        System.out.println("Car init..");
    }

    public void destroy(){
        System.out.println("Car destroy..");
    }

}
