package com.sonic.contentcenter.configuration;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * NacosSameClusterWeightedRule
 *
 * @author Sonic
 * @since 2020/4/25
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @SneakyThrows
    @Override
    public Server choose(Object key) {

        try {

            //拿到配置文件中的集群名称：BJ
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            String name = loadBalancer.getName();
            log.info("loadBalancer: {}", loadBalancer);
            log.info("loadBalancer name: {}", name);

//        拿到服务发现相关的api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            List<Instance> instances = namingService.selectInstances(name, true);
            List<Instance> sameClusterInstances = instances.stream().filter(instance -> Objects.equals(instance.getClusterName(), clusterName)).collect(Collectors.toList());

            List<Instance> instancesToBeChosen = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameClusterInstances)) {
                instancesToBeChosen = instances;
                log.warn("夸集群调用，name = {}， clusterName = {}, instances = {}", name, clusterName, instances);
            } else {
                instancesToBeChosen = sameClusterInstances;
            }

            Instance instance = ExtendBalancer.getHostByRandomWeightCustom(instancesToBeChosen);
            log.info("选择的是实例是 port={}, instance={}", instance.getPort(), instance);

            return new NacosServer(instance);
        } catch (Exception e) {
            log.error("Exception: {}", e);
        }

        return null;
    }
}

class ExtendBalancer extends Balancer {

    public static Instance getHostByRandomWeightCustom(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }

}
