## Springboot

### 概念
- @EnableAutoConfiguration
    - @AutoConfigurationPackage 扫描标注了@SpringBootApplication注解类所在的包的组件
    - AutoConfigurationImportSelector.class 导入自动化装配类，搜寻classpath下所有META-INF/spring.factories，然后把org.springframework.boot.autoconfigure.EnableAutoConfiguration中的类实例化bean，以及此类中通过@Bean注解标识的那些需要的bean
    


