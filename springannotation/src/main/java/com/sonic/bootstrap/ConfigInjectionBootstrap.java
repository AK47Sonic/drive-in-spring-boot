package com.sonic.bootstrap;

import com.sonic.injectconfig.ConfigInjection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ConfigInjectionBootstrap
 *
 * @auther Sonic
 * @since 2018/12/21
 */
@SpringBootApplication(scanBasePackages = "com.sonic.injectconfig")
public class ConfigInjectionBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigInjectionBootstrap.class, args);
        ConfigInjection ci = applicationContext.getBean(ConfigInjection.class);
        System.out.println(ci);
    }

}
