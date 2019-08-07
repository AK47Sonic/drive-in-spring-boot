package com.sonic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * SpringBootAppBootstrap
 *
 * @author Sonic
 * @since 2019/8/8
 */
@SpringBootApplication
public class SpringBootAppBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppBootstrap.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
