package com.sonic.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TestBaiduFeignClient
 * 脱离Ribbon的使用方式
 *
 * @author Sonic
 * @since 2020/4/26
 */
@FeignClient(value = "baidu", url = "http://www.baidu.com") //随便写，XXX都可以，但不能省略
public interface TestBaiduFeignClient {
    @GetMapping
    public String index();
}
