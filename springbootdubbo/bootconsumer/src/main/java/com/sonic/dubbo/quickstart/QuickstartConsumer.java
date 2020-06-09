package com.sonic.dubbo.quickstart;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sonic.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

@Component
public class QuickstartConsumer {

    @Reference(url = "dubbo://localhost:20880", interfaceClass = ServiceAPI.class)
    ServiceAPI serviceAPI;

    public void sendMessage(String message){
        System.out.println(serviceAPI.sendMessage(message));
    }

}
