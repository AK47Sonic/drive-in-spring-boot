package com.sonic.contentcenter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sonic.contentcenter.dao.content.ShareMapper;
import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.domain.entity.content.Share;
import com.sonic.contentcenter.feignclient.TestBaiduFeignClient;
import com.sonic.contentcenter.feignclient.TestUserCenterFeignClient;
import com.sonic.contentcenter.sentineltest.TestControllerBlockHandlerClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * TestController
 *
 * @author Sonic
 * @since 2020/4/5
 */
@Slf4j
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

    @Autowired(required = false)
    private TestUserCenterFeignClient testUserCenterFeignClient;

    @GetMapping("/test-get")
    public UserDTO query(UserDTO userDTO) {
        return testUserCenterFeignClient.query(userDTO);
    }

    @Autowired(required = false)
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

    @GetMapping("/test-sentinel-api")
    public String testSentinelAPI(@RequestParam(required = false) String a) {
        String resourceName = "test-sentinel-api";
        ContextUtil.enter(resourceName, "test-wfw");

//        定义一个sentinel保护的资源，名称test-sentinel-api
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName);

            if (StringUtils.isBlank(a)) {
                throw new IllegalArgumentException("a 不能为空");
            }
            // 被保护的业务逻辑
            return a;
// 如果被保护的资源被限流或降级，就会抛BlockException
        } catch (BlockException e) {
            log.warn("限流或者降级", e);
            return "限流或者降级";
        } catch (IllegalArgumentException e2) {
            // 统计 IllegalArgumentException发生的次数，占比
            Tracer.trace(e2);
            return "统计 IllegalArgumentException";
        } finally {
            if (entry != null) {
                entry.exit();
            }
            ContextUtil.exit();
        }
    }

    @GetMapping("/test-sentinel-resource")
    @SentinelResource(
            value = "test-sentinel-api",
            blockHandler = "block",
            blockHandlerClass = TestControllerBlockHandlerClass.class,
            fallback = "fallback"
    )
    public String testSentinelResource(@RequestParam(required = false) String a) {
        if (StringUtils.isBlank(a)) {
            throw new IllegalArgumentException("a can not be blank");
        }
        // 被保护的业务逻辑
        return a;
    }

    /**
     * 处理限流或者降级
     */
    public String block(String a, BlockException e) {
        log.warn("限流或者降级 block", e);
        return "限流或者降级 block";
    }

    /**
     * 1.5 处理降级
     * Sentinel 1.6 可以处理Throwable
     */
    public String fallback(String a) {
//        log.warn("限流或者降级 fallback", e);
        log.warn("限流或者降级 fallback");
        return "限流或者降级 fallback";
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test-rest-template-sentienl/{userId}")
    public UserDTO test(@PathVariable Integer userId) {
        return this.restTemplate.getForObject("http://user-center/users/{userId}", UserDTO.class, userId);
    }

}
