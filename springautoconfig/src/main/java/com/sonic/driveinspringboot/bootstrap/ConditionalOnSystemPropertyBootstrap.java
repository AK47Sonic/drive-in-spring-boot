package com.sonic.driveinspringboot.bootstrap;

import com.sonic.driveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Create by Sonic on 2018/11/25
 */

public class ConditionalOnSystemPropertyBootstrap {

    @Bean
    @ConditionalOnSystemProperty(name = "user.name",value = "Sonic")
    public String helloWorld(){
        return "Hello World, Sonic";
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext cac = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("Java8")
                .run(args);

        String helloWorld = cac.getBean("helloWorld", String.class);
        System.out.println("result: " + helloWorld);
        cac.close();

    }
}
