package com.sonic.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * MyUrlCleaner
 *
 * @author Sonic
 * @since 2020/5/2
 */
@Component
@Slf4j
public class MyUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        log.info("originUrl = {}", originUrl);

        // 让/shares/1与/shares/2的返回值相同
        // 返回/shares/{number}

        String[] split = originUrl.split("/");
        return Arrays.stream(split).map(string -> {
            if (NumberUtils.isNumber(string)) {
                return "{number}";
            }
            return string;

        }).reduce((a, b) -> a + "/" + b).orElse("");
    }
}
