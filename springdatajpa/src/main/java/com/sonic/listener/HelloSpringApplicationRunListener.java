package com.sonic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * HelloSpringApplicationRunListener
 *
 * @author Sonic
 * @since 2019/5/26
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    private Logger logger = LoggerFactory.getLogger(HelloSpringApplicationRunListener.class);

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        logger.info("starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        logger.info("environmentPrepared...{}", o);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        logger.info("contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        logger.info("contextLoaded...");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        logger.info("started...");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        logger.info("running...");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        logger.info("failed...");
    }
}
