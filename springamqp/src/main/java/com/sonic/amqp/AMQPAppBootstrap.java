package com.sonic.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AMQPAppBootstrap
 *
 * @author Sonic
 * @since 2019/6/2
 */
@EnableRabbit
@SpringBootApplication
public class AMQPAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AMQPAppBootstrap.class, args);
    }
}
