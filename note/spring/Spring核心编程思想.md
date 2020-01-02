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