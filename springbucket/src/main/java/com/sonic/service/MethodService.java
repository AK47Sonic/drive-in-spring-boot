package com.sonic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * MethodService
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Service("methodService")
public class MethodService implements ServiceInterface {
    private Logger logger = LoggerFactory.getLogger(MethodService.class);

    @Override
    public String getHelloWorld() {
        return "MethodService: Hello World";
    }

    public String getGoodBye() {
        logger.info("Proxy: {}", AopContext.currentProxy().getClass());
        return "MethodService: Good Bye";
    }
}
