package com.sonic.config;

import com.sonic.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @auther Sonic
 * @since 2018/12/12
 */
@Configuration
public class MainConfig {

    @Bean(value = "person02")
    public Person person01() {
        return new Person("Sky", 18);
    }

}
