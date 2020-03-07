### MyBatis

1. 核心类
- @ConfigurationProperties 是通过set方法注入
- org.mybatis.spring.SqlSessionFactoryBean
- org.apache.ibatis.session.Configuration
- org.springframework.core.io.support.PathMatchingResourcePatternResolver(ResourceLoader) 读mapper路径得到Resource
- @MapperScan 扫Mapper类，这样不需要每个类都使用@Mapper
- org.springframework.jdbc.datasource.DataSourceTransactionManager 事务
- org.mybatis.spring.SqlSessionTemplate 如果需要使用，就注入
- org.apache.ibatis.session.SqlSession 


2. XML 备注
- resultType： 返回值的类型
- `#{id}`从传过来的id参数中获取
- <mapper namespace="com.sonic.mapper.EmployeeMapper"> 和EmployeeMapper接口对应
- <select id="getEmployeeReturnComplexMap" id和接口方法名绑定
- 

3. 配置项
- setMapUnderscoreToCamelCase 数据库字段下划线转驼峰
- setDefaultStatementTimeout 超时时间
- setUseColumnLabel 列标签代替列名
- setLazyLoadingEnabled 开启全局延迟加载
- setCacheEnabled 开启缓存
- setTypeAliasesPackage 对指定的包下所有的entity批量起别名

4. Annotation
- @Alias("别名") 在XML中,可使用别名代替全类名

