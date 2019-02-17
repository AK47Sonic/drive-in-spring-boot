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
    4. 在`@Configuration`上使用`@Import`注册Bean
    
4. `@Configuration` 配置类
    1. `@Bean`给容器中注册一个bean
        1. 定义Bean name
            1. 通过方法名字，[demo](./src/main/java/com/sonic/config/MainConfig.java)
            2. `@Bean`的name，value属性, [demo](./src/main/java/com/sonic/config/MainConfig.java)
            
    2. `@Scope` 定义单例还是多例
        - `ConfigurableBeanFactory#SCOPE_PROTOTYPE` 多例， IOC容器创建完成，没有实例化，创建一次，调用一次
        - `ConfigurableBeanFactory#SCOPE_SINGLETON` 单例(默认)， IOC容器创建完成，已经实例化
    
    3. `@Lazy` 只对**单实例**懒加载有效，容器启动不创建对象，第一次调用bean创建对象。
        
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
    
    5. `@Conditional` 按照一定的条件进行判断，满足条件给容器中注册Bean，与内部`@Conditional`冲突时，先判断类上的条件，如果满足，再判断类内部条件。如果不满足，直接不注册。
        - 实现Condition接口 [demo](./src/main/java/com/sonic/config/MainConfig2.java)
    
    6. `@Import` 给容器中导入一个组件 [demo](./src/main/java/com/sonic/config/MainConfig2.java)
        - 类名XXX.class, 注册的Bean name是全类名, （有没有@Configuration不影响）
        - `ImportSelector` 返回String全类名
        - `ImportBeanDefinitionRegistrar`