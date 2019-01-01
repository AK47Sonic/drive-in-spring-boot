package com.sonic.aopconfig;

import com.sonic.aop.LogAspects;
import com.sonic.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AOP
 *
 * @EnableAspectJAutoProxy
 *  @import AspectJAutoProxyRegistrar.class
 *
 *
 * @auther Sonic
 * @since 2018/12/22
 */
//@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
