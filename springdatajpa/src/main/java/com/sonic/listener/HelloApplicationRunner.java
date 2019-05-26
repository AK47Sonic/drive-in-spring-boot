package com.sonic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * HelloApplicationRunner
 *
 * @author Sonic
 * @since 2019/5/26
 */
@Component
public class HelloApplicationRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(HelloApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("HelloApplicationRunner...");
    }
}
