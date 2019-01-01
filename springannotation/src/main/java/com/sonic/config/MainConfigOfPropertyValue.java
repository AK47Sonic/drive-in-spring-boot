package com.sonic.config;

import com.sonic.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MainConfigOfPropertyValue
 *
 * @auther Sonic
 * @since 2018/12/16
 */
//使用@PropertySource读取外部配置文件中的属性保存到运行的环境变量中,
// 加载完外部的配置文件以后使用${}取出配置文件的值
//@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValue {

    @Bean("sky")
    public Person person(){
        return new Person();
    }
}
