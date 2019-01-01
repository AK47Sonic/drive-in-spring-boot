package com.sonic.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TxBootstrap
 *
 * @auther Sonic
 * @since 2018/12/23
 */
public class TxBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TxBootstrap.class, args);
    }
}
