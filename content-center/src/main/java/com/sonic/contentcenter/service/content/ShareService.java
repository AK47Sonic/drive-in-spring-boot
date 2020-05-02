package com.sonic.contentcenter.service.content;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import com.sonic.contentcenter.dao.content.ShareMapper;
import com.sonic.contentcenter.domain.dto.content.ShareAuditDTO;
import com.sonic.contentcenter.domain.dto.content.ShareDTO;
import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.domain.entity.content.Share;
import com.sonic.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * ShareService
 *
 * @author Sonic
 * @since 2020/4/5
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private final UserCenterFeignClient userCenterFeignClient;

    @Autowired
    private Optional<UrlCleaner> urlCleanerOptional;

    public ShareDTO findById(Integer id) {
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();

//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//        String targetURL = instances.stream()
//                .map(i -> i.getUri() + "/users/{id}")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));
//
//        log.info("请求的目标地址：{}", targetURL);
//
//        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity(targetURL, UserDTO.class, userId);
//        UserDTO userDTO = forEntity.getBody();

        /**
         * restTemplate
         */
//        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity("http://user-center/users/{userId}", UserDTO.class, userId);
//        UserDTO userDTO = forEntity.getBody();

        /**
         * Feign
         */
        UserDTO userDTO = this.userCenterFeignClient.findById(userId);

        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());

        return shareDTO;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("http://localhost:8081/users/{id}", String.class, 1);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/users/{id}", String.class, 1);
        System.out.println(forEntity.getBody());

        /**
         * 200 OK
         * 500 服务端异常
         * 502 bad gateway
         */
        System.out.println(forEntity.getStatusCode());
    }

    public Share auditById(Integer id, ShareAuditDTO auditDTO) {

        Share share = this.shareMapper.selectByPrimaryKey(id);

        if (share == null) {
            throw new IllegalArgumentException("参数非法，该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法，该分享已经审核通过或审核不通过");
        }

        share.setAuditStatus(auditDTO.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);

//        异步执行
//        userCenterFeignClient.addBonus(id, 500);

        return share;
    }
}
