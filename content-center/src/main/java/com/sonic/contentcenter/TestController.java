package com.sonic.contentcenter;

import com.sonic.contentcenter.dao.content.ShareMapper;
import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.domain.entity.content.Share;
import com.sonic.contentcenter.feignclient.TestBaiduFeignClient;
import com.sonic.contentcenter.feignclient.TestUserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * TestController
 *
 * @author Sonic
 * @since 2020/4/5
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ShareMapper shareMapper;

    private final DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public List<Share> testInert() {

        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("xxx");
        share.setCover("xxx");
        share.setAuthor("Sonic");
        share.setBuyCount(1);

        this.shareMapper.insertSelective(share);
        List<Share> shares = this.shareMapper.selectAll();
        return shares;
    }

    @GetMapping("/test2")
    public List<ServiceInstance> getInstances() {
//         查询指定服务的所有实例的信息
        // consul/eureka/zookeeper 都可以使用discoveryClient
        return this.discoveryClient.getInstances("user-center");
    }

    @Autowired
    private TestUserCenterFeignClient testUserCenterFeignClient;

    @GetMapping("/test-get")
    public UserDTO query(UserDTO userDTO) {
        return testUserCenterFeignClient.query(userDTO);
    }

    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("baidu")
    public String baiduIndex() {
        return testBaiduFeignClient.index();
    }

    @Autowired
    private TestService testService;

    @GetMapping("test-a")
    public String testA() {
        this.testService.common();
        return "test-a";
    }

    @GetMapping("test-b")
    public String testB() {
        this.testService.common();
        return "test-b";
    }



}
