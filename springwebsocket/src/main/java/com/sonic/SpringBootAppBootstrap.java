package com.sonic;

import com.sonic.websocket.ChatRoomServerEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * SpringBootAppBootstrap
 *
 * @author Sonic
 * @since 2019/8/8
 */
@SpringBootApplication
@EnableWebSocket
public class SpringBootAppBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppBootstrap.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Bean
    public ChatRoomServerEndpoint chatRoomServerEndpoint(){
        return new ChatRoomServerEndpoint();
    }

}
