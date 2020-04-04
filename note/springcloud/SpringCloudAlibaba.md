## Spring Cloud Alibaba

### Alibaba Maven 镜像
- Minor： https://help.aliyun.com/document_detail/102512.html   
- 从aliyun的中央仓库下载
```xml
  <mirror>
    <id>aliyunmaven</id>
    <mirrorOf>central</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
```

### IDEA 快捷键
- Help -> Keymap Reference

### Maven
- Maven过滤掉test -Dmaven.test.skip=true 或 -DskipTests

### YAML
- YAML配置是有序的，而properties配置是无序的

### Spring boot
- Runner
    - org.springframework.boot.ApplicationRunner 参数封装为ApplicationArguments
    - org.springframework.boot.CommandLineRunner 可变参数
- Actuator
    - management.endpoint.health.show-details=always 显示health详情
    - 在application.properties定义如下，访问info: http://localhost:8080/actuator/info
    ```text
    info.app-name=spring-boot-demo
    info.author=Sonic
    info.email=rock_lee_aya@hotmail.com
    ```
    - 激活所有的Actuator端点 management.endpoints.web.exposure.include=* 如果使用YAML，需要'*'
    - 激活部分的Actuator端点 management.endpoints.web.exposure.include=metrics,health
    - metrics 度量： http://localhost:8080/actuator/metrics http://localhost:8080/actuator/metrics/jvm.memory.max
- jar中没有主清单属性是因为在MENIFEST.MF中没有
    ```text
    Start-Class: com.sonic.demo.ApplicationRunnerBootstrap
    Main-Class: org.springframework.boot.loader.JarLauncher
    ```
- 通过变量启动
    - 传参数
        - 环境变量
            - jar启动 java -jar springbootdemo-0.0.1-SNAPSHOT.jar --var_env=always
            - 通过System.getenv("var_env");获取
            ![env_var](../pic/Alibaba_env_var.JPG)
        - 程序参数Program arguments
            - 通过args获取
            - IDEA中模拟环境变量： --env=XXX
        - VM options
            - -Denv=dev
            - 通过System.getProperty("env");获取
    - 配置文件
        - ${env}可以从-Denv或者--env中获取
        - 配置文件中的属性都可以通过-Denv=XXX或者--env=XXX传入，进行设置。比如：-Dserver.port=8081，--server.port=8081 
    




