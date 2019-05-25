## Spring Boot

1. 监控（JMX）
    - health
        - http://localhost:8080/actuator/health
    - 通过Web查看beans
        - application.properties增加management.endpoints.web.exposure.include=*
        - 访问http://localhost:8080/actuator/beans
    - mappings
        - http://localhost:8080/actuator/mappings
    - env
        - http://localhost:8080/actuator/env

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
        - DataSourceTransactionManager
    - 申明式事务
        - @EnableTransactionManagement在spring boot中在TransactionAutoConfiguration中自动装配    

8. 配置Mybatis数据源
    - DataSource
        - HikariDataSource
    - SqlSessionFactory
        - SqlSessionFactoryBean.getObject();
    - PlatformTransactionManager
        - DataSourceTransactionManager(DataSource)
    - SqlSessionTemplate(SqlSessionFactory)

9. AOP   
    - DefaultAopProxyFactory implements AopProxyFactory(创建Aop)   
        - JdkDynamicAopProxy implements AopProxy   
        - ObjenesisCglibAopProxy implements AopProxy   
    - `@EnableAspectJAutoProxy` 在spring boot中在AopAutoConfiguration中自动装配
        - proxyTargetClass
        - exposeProxy 如果true，放入ThreadLocal，可以通过AopContext来获取   
    - AbstractAutoProxyCreator.postProcessBeforeInstantiation -> AspectJAwareAdvisorAutoProxyCreator.createProxy -> proxyFactory.getProxy
        
10. 数据库错误码解析 SQLErrorCodeSQLExceptionTranslator
    - 错误码定义：spring-jdbc-5.1.2.RELEASE.jar/org/springframework/jdbc/support/sql-error-codes.xml
    - sql-error-codes.xml注册自定义的CustomSQLErrorCodesTranslation

11. JPA
    - Java persistence API

12. Logback
    - AbstractLoggingSystem#initializeWithConventions 检测装载了哪个配置文件

13. @ConfigurationProperties VS @Value   
    ![区别](./pic/ConfigValue.JPG)

14. @PropertySource(value = {"classpath:application.properties"}) 加载额外属性文件

15. @ImportResource(value = {"classpath:beans.xml"}) 加载额外配置文件

16. Profiles
    - `--spring.profiles.active=dev` 程序参数
    - `-Dspring.profiles.active=dev` JVM参数
    - spring.profiles.active=dev 配置文件参数

17. `--spring.config.location=` 指定配置文件，互补配置

18. @PropertySource 不支持YAML，只支持properties， 但可以定制MixPropertySourceFactory
19. @TestPropertySource(value = {"classpath:externalized-config.properties"}) 只支持properties
20. @EnableConfigurationProperties // 相当于@Configuration或者 @Component + @ConfigurationProperties(prefix = "person")， bean name 不确定
21. application.yml定义debug=true调试自动装配依赖
22. logback-spring.xml VS logback.xml
    - 可以和<springProfile name="uat" />进行配合使用
    
23. WebMvcAutoConfiguration
    - addResourceHandlers (https://www.webjars.org/) 定义web资源和静态文件 ResourceProperties
    - welcomePageHandlerMapping 定义首页 ResourceProperties
    - FaviconConfiguration 定义图标
    - @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
        - addDefaultHttpMessageConverters 配置Converters
        - 重写这个方法getMessageConverters#extendMessageConverters，可以手动替换MessageConverter
     
24. thymeleaf
    - 可根据<properties/>切换版本
    
25. jsp
    - DispatcherServlet#processDispatchResult->#render
    - UrlBasedViewResolver#buildView
    - java/resources/webapp在一层
    - war包根是webapp下面一层
    
26. ViewResolver 视图解析器， 返回View对象
    - ContentNegotiatingViewResolver#resolveViewName解析视图, 组合所有的视图解析器。
    
27. DispatcherServlet
    1. FrameworkServlet#service(ServletRequest req, ServletResponse res)
    2. DispatcherServlet#doService->doDispatch->render

28. Converter, Formatter

29. 实现WebMvcConfigurer，来扩展Spring MVC 的功能
    - 例子
    ```text
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/ss").setViewName("success");
        }
    }
    ```
    - 原理：
        - WebMvcAutoConfigurationAdapter#EnableWebMvcConfiguration#DelegatingWebMvcConfiguration#setConfigurers注册Configurers到WebMvcConfigurerComposite
        - 遍历WebMvcConfigurerComposite调用WebMvcConfigurer的方法
        - 不需要@EnableWebMvc，使用此注解，则spring boot自动装配失效，完全手动配置（WebMvcAutoConfiguration中配置的bean都没有注册到容器中）

30. ResourceBundleMessageSource 国际化
    - MessageSourceAutoConfiguration#messageSource
    - MessageSourceProperties
    - LocaleResolver/Locale (WebMvcAutoConfiguration#localeResolver) bean名字一定要是localeResolver, 和源码保持一致
        - WebMvcAutoConfiguration#localeResolver
        - http://localhost:8080/success （Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7）
    
31. ThymeleafAutoConfiguration
    - ThymeleafProperties#DEFAULT_PREFIX
    - ThymeleafProperties#DEFAULT_SUFFIX
    
32. Model/ModelAndView/Map/ModelAndView 都可放在请求域中
33. ThymeleafViewResolver
    - REDIRECT_URL_PREFIX
    - FORWARD_URL_PREFIX
    - createView
    
34. Servlet
    - HttpServletResponse#sendRedirect(); 两次请求，所以地址变化
    - HttpServletRequest#getRequestDispatcher#forward(request,response); 一次请求，所以地址不变
    
35. Put方式
    - HiddenHttpMethodFilter 转变请求方式，因为表单提交只支持get/post, 不支持put/delete/patch

36. redirect VS forward
    - form表单：可以采用post或者get请求，客户端主动跳转，url地址会改变为提交后的地址
    - forward：forward是转发请求，不转发地址。服务器直接访问目标地址，把目标地址响应内容读取过来，然后再发送到客户端，客户端浏览器根本不知道服务器的内容是从其他的目标地址获取的，客户端url地址也不会改变。forward不但转发请求内容，还把请求的方式也转发了，所以forward的请求是get还是post取决于启用forward的源请求是post方式还是get方式，如：a.jsp 以 post 方式调b.jsp，那么b.jsp 以forward的方式访问c.jsp也会以post方式访问。
    - redirect：redirect是通过服务端向客户端发送状态码，在客户端跳转url，redirect都是get的方式请求，而且url地址会跳转到目标地址
    - redirect会进行两次request和两次response，并且地址和参数都被暴露出来，forward只会进行一次请求，效率更高而且可以隐藏信息

37. ErrorMvcAutoConfiguration
    - errorAttributes (DefaultErrorAttributes) 存放共享信息，给DefaultErrorViewResolver使用
    - basicErrorController (BasicErrorController) 处理error请求 （@RequestMapping("${server.error.path:${error.path:/error}}")）
        - errorHtml 产生HTML数据    
        ![MediaType](pic/MediaType.JPG)
        - error 产生json数据     
        ![MediaTypeJson](pic/MediaTypeJson.JPG) 
    - errorPageCustomizer (ErrorPageCustomizer) 系统出现错误以后，来到error请求进行处理(@Value("${error.path:/error}"))
    - DefaultErrorViewResolverConfiguration#conventionErrorViewResolver (DefaultErrorViewResolver)
        - 有模板引擎的情况下，在模板引擎根路径下建立error/404
        - 没有模板引擎情况下，在静态资源文件夹下static/error/404.html
        - 以上都没有，走默认Spring Boot错误页面 new ModelAndView("error", model);

38. MVC调用流程
    - DispatcherServlet#doService->#doDispatch
    - RequestMappingHandlerAdapter#handle->#handleInternal->#invokeHandlerMethod(modelFactory.initModel)
    - ServletInvocableHandlerMethod#invokeAndHandle
    - InvocableHandlerMethod#invokeForRequest
    - HandlerMethodReturnValueHandler 遍历获取HandlerMethodReturnValueHandler
        - RequestResponseBodyMethodProcessor#handleReturnValue -> HttpMessageConverter
        - ViewNameMethodReturnValueHandler

39. 主要类    
    - ModelAndViewContainer
    - RequestMappingHandlerMapping

40. @ControllerAdvice
    
41. 嵌入式Servlet容器
    - 修改server有关的配置(ServerProperties)
        - server.port=8081
        - server.servlet.context-path=/crud
        - server.tomcat.uri-encoding=UTF-8
    - ServletWebServerFactoryAutoConfiguration
        - WebServerFactoryCustomizer 通过WebServerFactoryCustomizerBeanPostProcessor#postProcessBeforeInitialization进行遍历
            - customize

42. 注册Servlet三大组件
    - Servlet (ServletRegistrationBean)
    - Filter (FilterRegistrationBean)
    - Listener (ServletListenerRegistrationBean)
    
43. DispatcherServletAutoConfiguration
    - DispatcherServletRegistrationBean