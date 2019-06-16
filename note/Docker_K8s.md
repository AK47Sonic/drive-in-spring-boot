## Docker and Kubernetes

1. 概念
- Docker： 微服务运行程序的容器
- Kubernetes： 管理容器，服务编排框架

2. 服务编排
- Mesos
- Swarm
- Kubernetes

3. docker 安装
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
 4. Dockerfile
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
    
5. Dockerfile demo
    - docker build -f CentosDockerfile -t mycentos:1.3 .
    - [Centos docker demo](./docker/CentosDockerfile)
    
6. K8s
    - https://github.com/liuyi01/kubernetes-starter
    - 
    
    