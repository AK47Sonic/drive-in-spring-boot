package com.sonic.ext;

import com.sonic.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ExtConfig
 *
 * @auther Sonic
 * @since 2018/12/23
 */
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue();
    }

}
