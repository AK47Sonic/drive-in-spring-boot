package com.sonic;

import com.sonic.servlet.MyServletRequestListener;
import com.sonic.spring.boot.MyFilter2;
import com.sonic.spring.boot.MyServlet2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import java.util.Arrays;

/**
 * HateoasAppBootstrap
 *
 * @author Sonic
 * @since 2019/8/3
 */
@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.sonic")
public class HateoasAppBootstrap
        extends SpringBootServletInitializer
{
    public static void main(String[] args) {
        SpringApplication.run(HateoasAppBootstrap.class, args);
    }

    @Bean
    public static ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean  servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyServlet2());
        servletRegistrationBean.setName("myservlet2");
        servletRegistrationBean.addUrlMappings("/spring/myservlet2");
        servletRegistrationBean.addInitParameter("myname", "myvalue");

        return servletRegistrationBean;
    }

    @Bean
    public static FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.setServletNames(Arrays.asList("myservlet2"));
//        filterRegistrationBean.addUrlPatterns("/spring/myservlet2");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
        return filterRegistrationBean;
    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyServletRequestListener());
        return servletListenerRegistrationBean;


    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HateoasAppBootstrap.class);
    }
}
