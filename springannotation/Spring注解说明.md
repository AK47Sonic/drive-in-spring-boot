## Spring 注解说明

1. AnnotationConfigApplicationContext
    - GenericApplicationContext
     - AbstractApplicationContext
     
2. DefaultListableBeanFactory
    - BeanFactory
    - SingletonBeanRegistry
    - BeanDefinitionRegistry
3. Spring中注册Bean的方式
    1. 在`@Configuration`中使用`@Bean`
    2. 在`@ComponentScan`扫描的包中包含@Service, @Control, @Repository, @Component
    3. 在`@ComponentScan`中使用includeFilters，符合规则的则注册为Bean
    
4. `@Configuration` 配置类
    1. `@Bean`给容器中注册一个bean
        1. 定义Bean name
            1. 通过方法名字，[demo](./src/main/java/com/sonic/config/MainConfig.java)
            2. `@Bean`的name，value属性, [demo](./src/main/java/com/sonic/config/MainConfig.java)
            
    2. `@Scope` 定义单例还是多例
        - `ConfigurableBeanFactory#SCOPE_PROTOTYPE` 多例， IOC容器创建完成，没有实例化，创建一次，调用一次
        - `ConfigurableBeanFactory#SCOPE_SINGLETON` 单例(默认)， IOC容器创建完成，已经实例化
    
    3. `@Lazy` **单实例**懒加载，容器启动不创建对象，第一次调用bean创建对象。
        
    4. `@ComponentScans` 指定多个`@ComponentScan`，多个`@ComponentScan`是并集的效果
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
    5. 