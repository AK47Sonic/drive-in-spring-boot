package com.sonic.contentcenter.feignclient;

import com.sonic.contentcenter.configuration.UserCenterFeignConfiguration;
import com.sonic.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UserCenterFeignClient
 *
 * @author Sonic
 * @since 2020/4/25
 */
@FeignClient(name = "user-center", configuration = UserCenterFeignConfiguration.class) //微服务的名称
public interface UserCenterFeignClient {

    /**
     * 构造http://user-center/users/{id}，并请求
     * @param Id
     * @return
     */
    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable(value = "id") Integer Id);

}
