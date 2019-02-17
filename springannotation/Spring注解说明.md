## Spring 注解说明

1. AnnotationConfigApplicationContext
    - GenericApplicationContext
     - AbstractApplicationContext
     
2. DefaultListableBeanFactory
    - BeanFactory
    - SingletonBeanRegistry
    - BeanDefinitionRegistry
    
3. `@Configuration` 配置类
    1. `@Bean`给容器中注册一个bean
        1. 定义Bean name
            1. 通过方法名字，[demo](./src/main/java/com/sonic/config/MainConfig.java)
            2. `@Bean`的name，value属性, [demo](./src/main/java/com/sonic/config/MainConfig.java)
    
    2. `@ComponentScans` 指定多个`@ComponentScan`，多个`@ComponentScan`是并集的效果
        - `@ComponentScan` 指定包扫描 [demo](./src/main/java/com/sonic/bootstrap/ConfigurationBootstrap.java)
            - basePackages/value
            - includeFilters 只包含，必须与useDefaultFilters=false一起使用，先禁用默认设置（包含@Service，@Component等），才能生效，符合规则的就会注册成Bean [demo](./src/main/java/com/sonic/bootstrap/ComponentScansBootstrap.java)
                - `@Filter`
                    - type 
                        - FilterType.ANNOTATION 指定注解
                        - FilterType.ASSIGNABLE_TYPE 指定类
                        - FilterType.CUSTOM 自定义，实现`org.springframework.core.type.filter.TypeFilter` [demo](./src/main/java/com/sonic/config/MyTypeFilter.java)
            - excludeFilters 不包含
                - `@Filter`