package com.sonic.contentcenter.feignclient.fallback;

import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.feignclient.UserCenterFeignClient;
import org.springframework.stereotype.Component;

/**
 * UserCenterFeignClientFallback
 *
 * @author Sonic
 * @since 2020/5/1
 */

@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {
    @Override
    public UserDTO findById(Integer Id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setWxNickname("一个默认用户");
        return userDTO;
    }
}
