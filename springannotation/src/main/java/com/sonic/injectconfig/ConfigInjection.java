package com.sonic.injectconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ConfigInjection
 *
 * @auther Sonic
 * @since 2018/12/21
 */
@Configuration
@ConfigurationProperties(prefix = "configinjection") //通过public set方法注入
public class ConfigInjection {
//    @Value("${configinjection.name}") //通过反射注入的，不需要set方法, private也可以
    private String name;
//    @Value("${configinjection.id}")
    private Integer id;

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConfigInjection{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
