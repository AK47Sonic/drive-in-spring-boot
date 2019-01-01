package com.sonic.driveinspringboot.bootstrap;

import com.sonic.driveinspringboot.configuration.HelloWorldAutoConfiguration;
import com.sonic.driveinspringboot.configuration.HelloWorldConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Create by Sonic on 2018/11/25
 */
@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String helloWorld =
                cac.getBean("helloWorld", String.class);
        System.out.println("helloWorld Bean : " + helloWorld);

        HelloWorldConfiguration hwc = cac.getBean(HelloWorldConfiguration.class);
        System.out.println("hwc : " + hwc.toString());

        HelloWorldAutoConfiguration hwac = cac.getBean(HelloWorldAutoConfiguration.class);
        System.out.println("hwac : " + hwac.toString());

//        ImportSelector hwis = cac.getBean(ImportSelector.class);
//        System.out.println("hwis : " + hwis);

        cac.close();
    }
}
