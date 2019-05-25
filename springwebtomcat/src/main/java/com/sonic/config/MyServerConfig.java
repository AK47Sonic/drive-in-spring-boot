package com.sonic.config;

import com.sonic.filter.MyFilter;
import com.sonic.listener.MyListener;
import com.sonic.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * MyServerConfig
 *
 * @author Sonic
 * @since 2019/5/24
 */
@Configuration
public class MyServerConfig {

    // http://localhost:8081/myServlet
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean<MyServlet> myServletServletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        myServletServletRegistrationBean.setLoadOnStartup(1);
        return myServletServletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
//        return new FilterRegistrationBean(new MyFilter(), myServlet());

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myServletListener() {
        ServletListenerRegistrationBean<MyListener> myListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return myListenerServletListenerRegistrationBean;
    }

}
