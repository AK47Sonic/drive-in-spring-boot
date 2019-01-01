package com.sonic.singletonconfig;

import com.sonic.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * TODO
 *
 * @auther Sonic
 * @since 2018/12/22
 */
@Configuration
public class MainSigletonConfig {

    @Bean("blue1")
    public Blue blue1(){
        return new Blue();
    }

    @Bean("blue2")
//    @Primary
    public Blue blue2(){
        return new Blue();
    }

    @Bean("blue3")
    @Primary
    public Blue blue3(){
        return new Blue();
    }

}
