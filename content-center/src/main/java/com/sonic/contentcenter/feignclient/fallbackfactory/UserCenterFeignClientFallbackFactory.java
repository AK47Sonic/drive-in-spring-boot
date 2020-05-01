package com.sonic.contentcenter.feignclient.fallbackfactory;

import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.feignclient.UserCenterFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * UserCenterFeignClientFallbackFactory
 *
 * @author Sonic
 * @since 2020/5/1
 */

@Component
@Slf4j
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {

    @Override
    public UserCenterFeignClient create(Throwable e) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO findById(Integer Id) {
                log.warn("远程调用被限流、降级了", e);
                UserDTO userDTO = new UserDTO();
                userDTO.setWxNickname("一个默认用户");
                return userDTO;
            }
        };
    }
}
