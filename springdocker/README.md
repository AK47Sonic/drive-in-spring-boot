## SpringDocker

1. @SpringBootApplication
    - @SpringBootConfiguration
        - @AutoConfigurationPackage 扫描当前类所在包的所有组件
    - @EnableAutoConfiguration 
        - @Import(AutoConfigurationImportSelector.class) 装载类路径下定义在META-INF/spring.factories中的EnableAutoConfiguration.class的类全名