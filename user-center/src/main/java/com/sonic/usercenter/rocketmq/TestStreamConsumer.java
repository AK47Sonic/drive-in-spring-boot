package com.sonic.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * TestStreamConsumer
 *
 * @author Sonic
 * @since 2020/5/6
 */
@Service
@Slf4j
public class TestStreamConsumer {

    @StreamListener(Sink.INPUT)
    public void receive(String msg) {
        log.info("receive msg: {}", msg);
    }

}
