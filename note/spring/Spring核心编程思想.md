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

#### DI/IOC
- DI是IOC的一种实现方式， 原本是A对象要调用B对象，即主动创建B对象，现在则需要依赖容器去创建B对象，才能调用，属于被动方式







