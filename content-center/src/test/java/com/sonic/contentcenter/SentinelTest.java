package com.sonic.contentcenter;

import org.springframework.web.client.RestTemplate;

/**
 * SentinelTest
 *
 * @author Sonic
 * @since 2020/4/26
 */
public class SentinelTest {

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            String object = restTemplate.getForObject("http://localhost:8010/actuator/sentinel", String.class);
            Thread.sleep(500);
        }
    }
}
