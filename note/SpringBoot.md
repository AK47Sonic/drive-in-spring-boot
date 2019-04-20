## Spring Boot

1. 通过Web查看beans
    - application.properties增加management.endpoints.web.exposure.include=*
    - 访问http://localhost:8080/actuator/beans

2. Datasource配置
    - DataSourceAutoConfiguration
        - DataSource
    - DataSourceTransactionManagerAutoConfiguration
        - DataSourceTransactionManager(implements PlatformTransactionManager)
    - JdbcTemplateAutoConfiguration
        - JdbcTemplate
    
3. 配置多数据源
    - 方式一：增加@Primary注解
    - 方式二：排除自动配置
        - DataSourceAutoConfiguration
        - DataSourceTransactionManagerAutoConfiguration
        - JdbcTemplateAutoConfiguration
![配置多数据源](pic/配置多数据源.JPG)
    
4. 属性参数列表
    - spring-boot-autoconfigure-2.2.0.M1.jar!/META-INF/spring-configuration-metadata.json

5. @Resource VS @Autowired
    - @Autowired按byType自动注入，而@Resource默认按 byName自动注入
    
6. Hikaricp
- 字节码级别优化，通过JavaAssist生成(Javaassist 就是一个用来处理 Java 字节码的类库)
- 大量小改进
    - FastList代替ArrayList
    - 无锁集合ConcurrentBag
    - 代理类的优化，invokestatic代替了invokevirtual
    
7. 事务
- PlatformTransactionManager
    - DataSourceTransactionManager
        - TransactionDefinition
            - Propagation
            - Isolation
            - TimeOut
            - Read-only status
        - TransactionStatus
        
![事务传播特性](pic/事务传播特性.JPG)
    
![事务隔离特性](pic/事务隔离特性.JPG)

- 编程式事务
    - TransactionTemplate
- 申明式事务


8. 配置Mybatis数据源
- DataSource
    - HikariDataSource
- SqlSessionFactory
    - SqlSessionFactoryBean.getObject();
- PlatformTransactionManager
    - DataSourceTransactionManager(DataSource)
- SqlSessionTemplate(SqlSessionFactory)

9 AOP
- DefaultAopProxyFactory implements AopProxyFactory(创建Aop)
    - JdkDynamicAopProxy implements AopProxy
    - ObjenesisCglibAopProxy implements AopProxy
    
- EnableAspectJAutoProxy
    - proxyTargetClass
    - exposeProxy 如果true，放入ThreadLocal，可以通过AopContext来获取
    
- AbstractAutoProxyCreator.postProcessBeforeInstantiation -> AspectJAwareAdvisorAutoProxyCreator.createProxy -> proxyFactory.getProxy





