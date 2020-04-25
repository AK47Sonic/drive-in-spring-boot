package com.sonic.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * UserCenterFeignConfiguration
 * 这个配置类不加@Configuration注解，否则必须放到@ComponentScan能扫描的包以外
 *
 * @author Sonic
 * @since 2020/4/26
 */
public class UserCenterFeignConfiguration {

    @Bean
    public Logger.Level level() {
        // 让Feign打印所有请求的细节
        return Logger.Level.FULL;
    }

}
