package com.sonic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncService
 *
 * @author Sonic
 * @since 2019/6/4
 */
@Service
public class AsyncService {

    private Logger logger = LoggerFactory.getLogger(AsyncService.class);

    // 这是一个异步方法
    @Async
    public void hello() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("say hello");
    }
}
