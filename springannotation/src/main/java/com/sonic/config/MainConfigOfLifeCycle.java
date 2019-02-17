package com.sonic.config;

import com.sonic.bean.Car;
import org.springframework.context.annotation.Configuration;

/**
 * initMethod destroyMethod
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@Configuration
public class MainConfigOfLifeCycle {

//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @Scope("prototype")
    public Car car(){
       return new Car();
    }
}
