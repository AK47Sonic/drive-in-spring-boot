package com.sonic.driveinspringboot.configuration;

import com.sonic.driveinspringboot.annotation.EnableHelloWorld;
import com.sonic.driveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Create by Sonic on 2018/11/25
 */
@Configuration //Spring 模式注解
@EnableHelloWorld //Spring 模块装配
@ConditionalOnSystemProperty(name = "user.name",value = "Sonic") //条件装配
public class HelloWorldAutoConfiguration {
}
