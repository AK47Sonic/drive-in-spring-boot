package com.sonic.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @auther Sonic
 * @since 2018/12/23
 */
@Service
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent applicationEvent) {
        System.out.println("UserService : " + applicationEvent);

    }

}
