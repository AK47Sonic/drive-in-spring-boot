package com.sonic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * MySecurityConfig
 *
 * @author Sonic
 * @since 2019/6/6
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 订制请求的授权规则

        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能, 如果没有登录， 就来到登录页面
        http.formLogin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("user1")
                        .password("password")
                        .roles("VIP1", "VIP2")
                        .build();

        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("user2")
                        .password("password")
                        .roles("VIP2", "VIP3")
                        .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
