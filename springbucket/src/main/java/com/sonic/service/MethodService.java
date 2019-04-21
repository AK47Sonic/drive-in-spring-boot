package com.sonic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MethodService
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Service("methodService")
public class MethodService implements ServiceInterface {
    private Logger logger = LoggerFactory.getLogger(MethodService.class);

    @Resource
    private ServiceInterface methodService;

    @Override
    public String getHelloWorld() {
        // 代理对象和目标对象是不同，hashcode竟然一样，也是醉了！
        logger.info("equal: " + (methodService == this));
        logger.info("MethodService instance: " + this);
        logger.info("MethodService instance class: " + this.getClass());
        return "MethodService: Hello World";
    }

    public String getGoodBye() {
        logger.info("Proxy: {}", AopContext.currentProxy().getClass());
        this.getHelloWorld();
        logger.info("MethodService instance: " + this);
        logger.info("MethodService instance class: " + this.getClass());
        return "MethodService: Good Bye";
    }
}
