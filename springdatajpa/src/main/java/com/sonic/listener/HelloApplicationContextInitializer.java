package com.sonic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * HelloApplicationContextInitializer
 *
 * @author Sonic
 * @since 2019/5/26
 */
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private Logger logger = LoggerFactory.getLogger(HelloApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        logger.info("HelloApplicationContextInitializer...");
    }
}
