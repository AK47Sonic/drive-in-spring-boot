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

- Spring boot Initializer
![spring_init](../pic/Alibaba_spring_init.JPG)

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
    
- Profile
    - yaml
        ![profile](../pic/Alibaba_profile.JPG)
        ![profile2](../pic/Alibaba_profile2.JPG)
        - 设置默认profile：application.yml中加spring:profiles:active:dev
        - 或者建立多个yaml：application-dev.yml
    - properties
        - 建立多个properties：application-dev.properties
        - 设置默认profile：application.properties中加spring.profiles.active=dev
    - 总结
        - 共用的放在application.XXX中，非共用的放在application-env.XXX中    

- structure
    - domain
        - dto （data transfer object）
        - entity 数据库对应
        - enums 枚举

- @Autowired和@Resource区别： https://blog.csdn.net/weixin_40423597/article/details/80643990
- Intellij IDEA中Mybatis Mapper自动注入警告的6种解决方案：https://www.imooc.com/article/287865

### 服务拆分方法论  
- 领域驱动设计（Domain Driven Design简称DDD）
- 面向对象（by name、by verb）

### 服务拆分
- 职责划分
- 通用性划分

### 数据库
- 测试connection
![db_gui](../pic/Alibaba_db_gui.JPG)
- 创建console
![db_gui2](../pic/Alibaba_db_gui2.JPG)

### API文档
https://t.itmuch.com/doc.html

### 注册微信小程序
https://mp.weixin.qq.com/
![weixin](../pic/Alibaba_weixin.JPG)

### 前端代码
https://github.com/eacdy/itmuch-miniapp
- 安装nodejs
    - 检测node -v
- 参考 https://github.com/eacdy/itmuch-miniapp

### 通用Mapper （MyBatis 插件）
- https://github.com/abel533/Mapper
- 整合lombok https://github.com/abel533/Mapper/wiki/4.1.mappergenerator
    
### Lombok
- https://projectlombok.org/setup/maven
- 注解
    - @Getter
    - @Setter
    - @ToString
    - @EqualsAndHashCode
    - @NoArgsConstructor 无参构造
    - @AllArgsConstructor 所有参数构造
    - @RequiredArgsConstructor 只对final申明的字段进行构造函数 @RequiredArgsConstructor(onConstructor = @__(@Autowired)) 在构造函数上加@Autowired注解
    - @Builder 建造者模式
    - @Data

    




