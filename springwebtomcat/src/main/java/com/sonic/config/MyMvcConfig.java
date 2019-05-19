package com.sonic.config;

import com.sonic.component.LoginHandlerIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MyMvcConfig
 *
 * @author Sonic
 * @since 2019/5/14
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ss").setViewName("success");
        registry.addViewController("main.html").setViewName("content");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/ss", "/", "/content");
    }

    //    @Bean
//   public LocaleResolver localeResolver(){
//        return new MyLocaleResolver();
//    }
}
