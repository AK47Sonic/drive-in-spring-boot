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
- org.apache.ibatis.type.TypeHandler java和数据库之间的类型转换
- plugins
    - Executor
    - ParameterHandler
    - ResultSetHandler
    - StatementHandler
- boolean/Integer Mapper方法返回值（影响行数），MyBatis会自动封装，无需在XML中定义resultType


2. XML 备注
- resultType： 返回值的类型
- `#{id}`从传过来的id参数中获取
- <mapper namespace="com.sonic.mapper.EmployeeMapper"> 和EmployeeMapper接口对应
- <select id="getEmployeeReturnComplexMap" id和接口方法名绑定
- 内置别名映射：基本类型long -> _long, 引用类型Long -> long
- parameterType="Employee" 指定参数类型，可省略
- useGeneratedKeys="true" keyProperty="id" 使用生成的主键，并将值封装给bean的id属性
- <insert> 中使用<selectKey keyProperty="id" order="before" resultType="int"> 获取sequence的值，封装为Integer变量，并放到id字段中，供insert SQL语句使用
- resultType/resultMap 两者必须有其一



3. 配置项
- setMapUnderscoreToCamelCase 数据库字段下划线转驼峰
- setDefaultStatementTimeout 超时时间
- setUseColumnLabel 列标签代替列名
- setLazyLoadingEnabled 开启全局延迟加载
- setCacheEnabled 开启缓存
- setTypeAliasesPackage 对指定的包下所有的entity批量起别名（类名小写），别名不区分大小写
- setMapperLocations 指定Mapper.xml所在位置

4. Annotation
- @Alias("别名") 在XML中,可使用别名代替全类名，覆盖setTypeAliasesPackage的设置
- @Select 直接在Mapper接口方法上写sql，来省略Mapper.xml
- @MapperScan 指定Mapper接口所在的包，批量注册
- @Mapper 单一注册Mapper接口
- 


