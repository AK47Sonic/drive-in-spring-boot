## Spring 核心编程思想

#### Aware
- ApplicationContextAware
- BeanFactoryAware

#### BeanPostProcessor

#### 设计模式
- 观察者模式： SimpleApplicationEventMulticaster#multicastEvent
- 组合模式：CompositeCacheManager.cacheManagers
- 模板方法模式：JdbcTemplate#execute(CallableStatementCreator csc, CallableStatementCallback<T> action),CallableStatementCreator需要实现

#### 代理
- AopProxy
    - CglibAopProxy （依赖asm）
    - JdkDynamicAopProxy

#### 外部化配置
- Environment是多个PropertySource

#### 泛型
- GenericTypeResolver
- ResolvableType
- ParameterizedType

#### Enable
- EnableCaching
- EnableTransactionManagement
- EnableWebMvc

#### Spring 核心价值
- 面向对象编程
- 面向切面编程
- 面向元编程
- 面向模块编程
- 面向函数编程
- OOP
- Ioc/DI
- DDD
- TDD
- EDP
- PF
 
 #### 面试题
 ![spring模块](../pic/Spring模块.JPG)
- core: 资源管理（Resource）, 泛型处理（GenericTypeResolver）
- beans: 依赖查找（BeanFactory），依赖注入（AutowiredAnnotationBeanPostProcessor）
- context：事件驱动（ApplicationEvent）， 注解驱动（Component, ComponentScan），模块驱动（EnableCaching, EnableAspectJAutoProxy）
 ![Ioc](../pic/IOC.JPG)
 


#### DI/IoC
- DI是IoC的一种实现方式， 原本是A对象要调用B对象，即主动创建B对象，现在则需要依赖容器去创建B对象，才能调用，属于被动方式
- IoC实现方式：
    - Java Beans
    - Java ServiceLoader SPI
    - JNDI
- Ioc 其实是一种推的模式（通常使用的是拉的模式），观察者模式的扩展都属于Ioc
  
#### Java Beans
- Introspector(自省)  
- PropertyEditorSupport(接口：PropertyEditor)
- 构造注入/Set注入（构造注入：属性申明final，不变对象）








