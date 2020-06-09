package com.sonic.dubbo.quickstart;

import com.alibaba.dubbo.config.annotation.Service;
import com.sonic.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = ServiceAPI.class)
public class QuickstartServiceImpl implements ServiceAPI {

    @Override
    public String sendMessage(String message) {
        return "quickstart-provider-message="+message;
    }
}
