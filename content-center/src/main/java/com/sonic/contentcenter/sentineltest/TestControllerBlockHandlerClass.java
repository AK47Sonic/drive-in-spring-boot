package com.sonic.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * TestControllerBlockHandlerClass
 *
 * @author Sonic
 * @since 2020/5/1
 */
@Slf4j
public class TestControllerBlockHandlerClass {

    /**
     * 处理限流或者降级
     *
     */
    public static String block(String a, BlockException e) {
        log.warn("限流或者降级 block", e);
        return "限流或者降级 block";
    }


}
