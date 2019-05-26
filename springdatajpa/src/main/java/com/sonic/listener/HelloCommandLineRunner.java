package com.sonic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * HelloCommandLineRunner
 *
 * @author Sonic
 * @since 2019/5/26
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(HelloCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("HelloCommandLineRunner...");
    }
}
