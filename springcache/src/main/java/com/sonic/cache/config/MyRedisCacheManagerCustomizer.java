package com.sonic.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

/**
 * MyRedisCacheManagerCustomizer 没有可以修改default config  的方法
 * @author Sonic
 * @since 2019/6/1
 */
@Component
public class MyRedisCacheManagerCustomizer implements CacheManagerCustomizer {

    private Logger logger = LoggerFactory.getLogger(MyRedisCacheManagerCustomizer.class);
    @Override
    public void customize(CacheManager cacheManager) {
        if (cacheManager instanceof RedisCacheManager) {
//            RedisCacheManager redisCacheManager = (RedisCacheManager)cacheManager;
//            Map<String, RedisCacheConfiguration> cacheConfigurations = redisCacheManager.getCacheConfigurations();
//            logger.info("cacheConfigurations:{}", cacheConfigurations);
        }

    }
}
