## MyBatisPlus

1. 核心类
- MapperProxy 生成Mapper的代理类
- MybatisSqlSessionFactoryBean MyBatisPlus的SqlSessionFactoryBean
- MybatisConfiguration MyBatis核心配置文件
- MapperBuilderAssistant 把封装好的MappedStatement放入MybatisConfiguration
- MappedStatement 存放Mapper方法和sql
- SqlSource 存放sql
- BaseStatementHandler
- InterceptorChain 插件调用，plugin方法将source转为代理对象，intercept最终会被执行
- PaginationInterceptor 分页插件