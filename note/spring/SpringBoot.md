### Spring Boot
1. Java 和 C相比，性能缺点
    - 类型内存消耗较大（java int 32, c int 16)
    - 垃圾回收（java被动回收，c主动回收）
    - Java full gc 有停顿
    - Java只有线程没有协程

2. 静态标准化优化技术
    - 资源变化
        1. 响应头(response headers)：Last-Modified: Wed, 03 Sep 2014 10:00:27 GMT
        2. 请求头(request headers)：If-Modified-Since: Wed, 03 Sep 2014 10:00:27 GMT
    - 资源缓存
        1. 响应头：Etag: "1ec5-502264e2ae4c0" (缓存的key)
        2. 请求头：If-None-Match: "1ec5-502264e2ae4c0"
        
3. HTTP协议中的Accept与Content-Type的区别
    - 类型不同  
        类型不同Accept属于请求头， Content-Type属于实体头。Http报头分为通用报头，请求报头，响应报头和实体报头。
        请求方的HTTP报头结构：通用报头|请求报头|实体报头
        响应方的HTTP报头结构：通用报头|响应报头|实体报头
    - 作用不同  
        Accept代表发送端（客户端）希望接受的数据类型。 比如：Accept：text/xml; 代表客户端希望接受的数据类型是xml类型。
        Content-Type代表发送端（客户端|服务器）发送的实体数据的数据类型。 比如：Content-Type：text/html; 代表发送端发送的数据格式是html。
    - 二者合起来， Accept:text/xml； Content-Type:text/html ，即代表希望接受的数据类型是xml格式，本次请求发送的数据的数据格式是html。
    
4. Java Web 服务器
    - Servlet 容器（Tomcat，Jetty）
    - 非Servlet容器（Undertow）

5. URI (https://www.baidu.com/s) 和 queryString（?ie=utf-8&f=8&rsv_bp=1）

6. Tomcat启动SpringBoot顺序
    1. 在jar的META-INFO/services/javax.servlet.ServletContainerInitializer中定义实现类SpringServletContainerInitializer
    2. 在SpringServletContainerInitializer中，找@HandlesTypes(WebApplicationInitializer.class)的实现类启动

7. Spring Boot 与 JSP 结合
    - spring-configuration-metadata.json中spring.mvc.view.prefix
    
8. 模板引擎属性一般实现`AbstractViewResolverProperties`

9. Spring Front Controller是DispatcherServlet, @Controller是Application Controller 或者叫 Command

10. 调用顺序
    HttpServlet#service -> DispatcherServlet#doDispatch -> @Controller方法 

11. Rest常用注解
    - @RequestParam(required=false,defaultValue="Empty") 也支持Servlet规范HttpServletRequest获取
    - @PathVariable
    - @Controller
    - @RestController
    - @ResponseBody
    - @RequestMapping(consumes={MediaType.APPLICATION_JSON_VALUE，...}, produces=MediaType.APPLICATION_JSON_VALUE) 使用哪种accept类型判断使用什么content-type
    - @RequestHeader(value="Accept")
    - @CookieValue
    - RequestEntity
    - ResponseEntity

12. 配置Json/XML
    - WebMvcConfigurationSupport  
    
13. HttpStatus
    - 401
    
14. 监控
    - management.endpoint
    
15. Servlet
    - Servlet
        - init
    - Filter
        - init
    - ServletContext
    - HttpSession
    - HttpServletRequest
    - HttpServletResponse
    - Cookie
    - Event (ServletContextEvent -> EventObject)
    - Listener (ServletContextListener -> EventListener, ContextLoaderListener)
    
16. Servlet 3.0
    - 注解
        - WebServlet
        - WebFilter
        - WebListener
        - ServletSecurity
        - HttpMethodConstraint
        - HttpConstraint
        - WebInitParam
    - 上下文
        - AsyncContext/Event/Listener
    - ServletContainerInitializer(搭配使用HandlesTypes)
        - META-INF/services (ServiceLoader)
    - Spring Boot 整合
        - ServletRegistrationBean
        - FilterRegistrationBean
        - RequestContextHolder 获取request
        - ServletListenerRegistrationBean
        
17. 整合JSP
    - 引入Jasper和Jstl
    - 实现SpringBootServletInitializer
    - 配置WebMvcProperties （spring.mvc.view.prefix）
    - 排查 InternalResourceViewResolver#buildView

18. WebSocket
    - 长连接
    
19. Tomcat
    - Engine(Catalina)
    - Host
    - Context
    
20. Embedded Tomcat
    - ServletWebServerFactoryCustomizer(WebServerFactoryCustomizer) 自定义Tomcat配置
    
21. Statement
    - Statement: SQL注入问题
    - PreparedStatement： 预编译，无SQL注入问题
    - CallableStatement: 存储过程
    

    
     

    
