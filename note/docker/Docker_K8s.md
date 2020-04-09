## Docker and Kubernetes

1. 概念
- Docker： 微服务运行程序的容器
    - image： 模板
    - container: 实例
    - 仓库： 集中存放镜像文件的场所
    - 仓库注册服务器（Registry）： 仓库注册服务器上存放着多个仓库，每个仓库包含多个镜像，每个镜像有不同tag
- Kubernetes： 管理容器，服务编排框架

2. 服务编排
- Mesos
- Swarm
- Kubernetes

3. docker
    - 查看linux版本, 6.5以上版本
        - uname -r
        - cat /etc/redhat-release
    - 版本
        - CE 社区版
        - EE 企业版
        
4. docker 安装
    - Linux 6
        1. `yum install -y epel-release`
        2. `yum install -y docker-io`
        3. 配置 /etc/sysconfig/docker
        4. 后台启动docker： service docker start
        5. 检查版本 `docker version`
    - Linux 7
        - https://docs.docker.com/install/linux/docker-ce/centos/
    - 阿里云 https://cr.console.aliyun.com/cn-shanghai/instances/mirrors
    - ```text
        sudo mkdir -p /etc/docker
        sudo tee /etc/docker/daemon.json <<-'EOF'
        {
          "registry-mirrors": ["https://v7v7v14d.mirror.aliyuncs.com"]
        }
        EOF
        sudo systemctl daemon-reload
        sudo systemctl restart docker
        ```
5. Dockerfile
    - FROM
    - MAINTAINER
    - RUN
    - EXPOSE
    - WORKDIR
    - ENV
    - ADD 复制+解压
    - COPY
    - VOLUME
    - CMD 多个命令，只有最后一个生效，并且会被docker run的参数替换
    - ENTRYPOINT 
    - ONBUILD 触发器，父镜像的ONBUILD会被子镜像构建时触发
    
6. Dockerfile demo
    - docker build -f CentosDockerfile -t mycentos:1.3 .
    - [Centos docker demo](CentosDockerfile)   
7. K8s
    - Master
        - API Server  负责接收和处理请求
        - Scheduler 调度容器创建的请求
        - Controller Manager 确保控制器健康，而控制器确保容器处于健康状态
            - 控制器
                - Replication Controller 老的版本（核心）
                - ReplicaSet 新的版本（核心）
                    - Deployment 负责管理无状态应用， 使用ReplicaSet
                        - HPA（HorizontalPodAutoscaler） 水平Pod自动伸缩控制器
                    - StatefulSet 负责管理有状态应用， 使用ReplicaSet
                    - DaemonSet 确保在每一个结点上运行一个副本，而不是随意运行， 使用ReplicaSet
                    - Job, Cronjob 运行作业和周期性作业， 使用ReplicaSet
    - Node
        - Kubelet 调度的执行者，来运行docker
        - Kube-proxy 维护servcie转发
        - etcd （key value存储，类似zookeeper），数据库
        - DNS 内部设定一个范围
        - CNI：提供网络
            - flannel 网络配置
            - calico 网络配置 网络策略
        - Pod Kubernetes 调度的最小单位，一个Pod上可以包含多个容器，共享同一个网络名称空间，地址相同，共享存储卷 
        - Label Selector 通过标签来过滤资源对象，（key，value） 比如： app=Nginx，pod可以用，其他资源也可用
    - 网络
        - 结点网络（node）
        - 集群网络（service）
        - Pod网络 （pod）


8. 容器编排3剑客
    1. Docker
        - Docker compose 单机编排
        - Docker swarm 多机编排
        - Docker machine 把主机初始化为可以加入swarm成员的机器的预处理工具
    2. Mesos， marathon
    3. Kubernetes
    
9. DevOps 
    - CI（Continuous Integration） : 持续集成
    - CD（Continuous delivery）： 持续交付
    - CD（Continuous deployment）： 持续部署
10. Openshift 是Paas平台，底层是Kubernetes


   

    