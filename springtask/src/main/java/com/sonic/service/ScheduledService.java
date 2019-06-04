package com.sonic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * ScheduledService
 *
 * @author Sonic
 * @since 2019/6/4
 */
@Service
public class ScheduledService {

    private Logger logger = LoggerFactory.getLogger(ScheduledService.class);

    // second, minute, hour, day, month, day of week
//    @Scheduled(cron = "0 * * * * MON-SAT")
//    @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
    @Scheduled(cron = "0-4 * * * * MON-SAT") //0-4秒执行一次
//    @Scheduled(cron = "0/4 * * * * MON-SAT") //每4秒执行一次
    public void hello(){
        logger.info("hello...");
    }


}
