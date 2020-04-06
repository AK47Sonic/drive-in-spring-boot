package com.sonic.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * UserCenterRibbonConfiguratrion
 *
 * @author Sonic
 * @since 2020/4/6
 */
@Configuration
// 个性化配置
//@RibbonClient(name = "user-center", configuration = RibbonConfiguration.class)
//全局配置
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
