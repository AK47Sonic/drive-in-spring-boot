### MyBatis

1. 核心类
- org.apache.ibatis.binding.MapperProxy
- org.apache.ibatis.reflection.ParamNameResolver.getNamedParams 处理@Param，并封装参数map
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
- 参数
    - 单个参数： #{参数名}直接取出参数值，写什么名字都OK
        - 对象， #{属性名}
        - Map，#{key}取出对应的值
        - Collection, 封装为map，通过#{collection} #{list}取值
        - Array, 封装为map，通过#{array}
        - 使用@Param，则封装为Map，通过#{key}获取
    - 多个参数：多个参数会被封装成一个map，#{}是从map中取key的值 param1...paramN
        - 命名参数：明确指定封装参数时map的key， @Param("id") @Param("lastName"), 通过#{id}从map中取值
    - 举例：
        - Integer id， @Param("e") Employee emp -> id => #{param1} lastName => #{param2.lastName/e.lastName}
        - List<Integer> ids -> #{list[0]}
    - 备注：param1...paramN是默认支持的，arg0...argN是要使用setUseActualParamName开启


2. XML 备注
- resultType： 返回值的类型
- `#{id}`从传过来的id参数中获取
- <mapper namespace="com.sonic.mapper.EmployeeMapper"> 和EmployeeMapper接口对应
- <select id="getEmployeeReturnComplexMap" id和接口方法名绑定
- 内置别名映射：基本类型long -> _long, 引用类型Long -> long
- parameterType="Employee" 指定参数类型，可省略
- useGeneratedKeys="true" keyProperty="id" 使用生成的主键，并将值封装给bean的id属性
- <insert> 中使用<selectKey keyProperty="id" order="before" resultType="int"> 获取sequence的值，封装为Integer变量，并放到id字段中，供insert SQL语句使用
- resultType/resultMap <select>两者必须有其一
- `${}`: 直接替换 
- `#{}`：占位符，预编译， PreparedStatement, 防止sql注入
- 参数： javaType, jdbcType, mode(存储过程)， numericScale, resultMap, typeHandler, jdbcTypeName, expression
    - jdbcType: 当某些数据库插入null值时候，mybatis映射到org.apache.ibatis.type.JdbcType.OTHER，MySQL认识，但Oracle不认识，所以需要手动指定#{email,jdbcType=NULL}
- 返回值
    - 是一个集合，则resultType是集合中对象的类型List<Employee> -> Employee, List<Map> -> map
    - 是一个map
        - key, value -> resultType = map
        - key, 对象 -> 使用@MapKey指定key， resultType = 对象类名
    - resultMap
        - type: 是bean类名， id 定义主键，底层会有优化，其他用result
        - 如果在resultMap中没有定义的属性，MyBatis会自动封装（列名和字段名对应），如果返回的列名没有匹配的属性名，则自动忽略
        - 如果是1对1情况下
            - 可以直接使用<result column="dept_name" property="department.departmentName"/> 对象名.属性来赋值
            - 或使用 <association property="department" javaType="Department"> <id column="did" property="id"/>
            - 或使用分步查询 <association property="department" select="com.sonic.mapper.DepartmentMapper.getDeptById" column="{id=d_id}"> 其中select指定的是select Id，多列封装map写法{key1=column1，key2=column2}
        - 如果是1对多情况下
            - 使用<collection property="employees" ofType="Employee">, 务必注意，这里使用的是**ofType**。 
            - 或使用分步查询 <collection property="employees" select="com.sonic.mapper.EmployeeMapperPlus.getEmployeesByDeptId" column="{deptId=id}" fetchType="eager"> 其中select指定的是select Id，多列封装map写法{key1=column1，key2=column2}
        - 使用discriminator case 封装复杂逻辑
- 内置参数
    - _parameter
        - 单个参数：_parameter就是这个参数
        - 多个参数：参数会被封装为一个map，_parameter就是代表这个map
    - _databaseId: 需配置databaseIdProvider
- <bind name="_lastName" value="'%' + lastName + '%'"/> 使用#{_lastName}获取
- <sql id="insertColumn">last_name,email,gender,d_id</sql> <include refid="insertColumn"/> sql复用
- <include> 中可以自定义一些property，在sql标签中使用${var}，当sql被执行时，property变量会被定义的值替换

3. 配置项
- setMapUnderscoreToCamelCase 数据库字段下划线转驼峰
- setDefaultStatementTimeout 超时时间
- setUseColumnLabel 列标签代替列名
- setLazyLoadingEnabled 开启全局延迟加载
- setCacheEnabled 开启缓存
- setTypeAliasesPackage 对指定的包下所有的entity批量起别名（类名小写），别名不区分大小写
- setMapperLocations 指定Mapper.xml所在位置
- setUseActualParamName 默认true->支持通过arg0 arg1...argN 获取, false-> 0, 1...n
- setJdbcTypeForNull(JdbcType.NULL); 对于不支持jdbcType.OTHER的数据库，可以设置为jdbcType.NULL
- setLazyLoadingEnabled(true), setAggressiveLazyLoading(false) 分步查询，开启懒加载, 只要获取另一个对象的属性，则会加载（包括toString）
- setCacheEnabled(true); 开启2级缓存

4. Annotation
- @Alias("别名") 在XML中,可使用别名代替全类名，覆盖setTypeAliasesPackage的设置
- @Select 直接在Mapper接口方法上写sql，来省略Mapper.xml
- @MapperScan 指定Mapper接口所在的包，批量注册
- @Mapper 单一注册Mapper接口
- 

5. 动态SQL标签（OGNL）http://commons.apache.org/proper/commons-ognl/language-guide.html
- 转义：https://www.w3school.com.cn/tags/html_ref_entities.html
- 要么使用and或''，要么使用转义字符&amp;&amp;或&quot;&quot;
- <if test="id != null and id != 0"> id 为属性参数值，满足条件就走
- <where> 会把第一个and或者or去掉
- <trim prefix="where" prefixOverrides="and"> 整体之前或之后加字符串，Overrides之前或之后移除字符串
- <choose> <when test="id != 0"> 只走一个条件，其他条件跳过
- <set> 去除尾部，
- <foreach collection="_parameter.get(&quot;list&quot;)" item="item_id" separator="," open="(" close=")" index="i"> 
    - collection：指定封装map的key 
    - separator： 每个元素之后添加
    - open/close: 整个foreach条件之前或之后添加
    - index: 遍历list的时候是索引，遍历map的时候是map的key

6. 缓存
- 1级缓存： SqlSession级别的一个map，一直开启的
- 2级缓存： 基于namespace级别的缓存（mapper), SqlSession关闭后，会把一级缓存的数据放入二级缓存。如果没有关闭，是不会放过去的。
    - 开启2级缓存
        - setCacheEnabled(true); 
        - 在Mapper.xml中增加<cache>
            - eviction: 缓存回收策略， FIFO， LRU。。。
            - flushInterval： 缓存刷新间隔， 秒
            - readOnly： true 只读 false 非只读， 读数据一致性问题
            - size： 缓存存放多少元素
            - type：自定义缓存全类名
        - POJO需要实现序列化接口 
        - <select id="getEmployee" resultType="com.sonic.bean.Employee" useCache="false">   单条语句关闭2级缓存
        - <update id="updateEmployee"  flushCache="true"> 增删改默认flushCache="true"，清除一级二级缓存
- 查询缓存顺序： 
    1. 二级缓存
    2. 一级缓存
- 整合第三方cache
    - 导入mybatis-echcache依赖
    - 添加echcache.xml
    - 在Mapper.xml中添加<cache 指定type>， 在其他文件中使用<cache-ref namespace指定相关mapper>
    
7. 运行原理
- 获取SqlSessionFactory： org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
- 获取SqlSession：org.apache.ibatis.session.defaults.DefaultSqlSession 包含了Configuration和Executor
- 获取接口的实现类对象（MapperProxy）
- org.apache.ibatis.builder.xml.XMLConfigBuilder 解析XML
    - configuration 包含所有MyBatis配置
- org.apache.ibatis.mapping.MappedStatement 封装一个增删改查语句
    - sqlSource 中sql包含完整sql语句
- org.apache.ibatis.executor.Executor
    - org.apache.ibatis.executor.CachingExecutor
    - org.apache.ibatis.executor.BatchExecutor
    - org.apache.ibatis.executor.SimpleExecutor
- 每一个拦截器Interceptor的plugin方法会被调用
- org.apache.ibatis.binding.MapperProxy (InvocationHandler)
- StatementHandler 执行SQL语句
    - ParameterHandler 设置预编译参数
        - TypeHandler 进行数据库类型和JavaBean类型的映射
    - ResultSetHandler 处理结果
        - TypeHandler 进行数据库类型和JavaBean类型的映射
- 总流程；MapperProxy -> DefaultSqlSession -> Executor -> StatementHandler -> ParameterHandler -> TypeHandler -> ResultSetHandler -> TypeHandler

8. 插件
- 步骤
    1. 实现org.apache.ibatis.plugin.Interceptor
    2. @Intercepts指定拦截的方法@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)})
    3. 注册插件factoryBean.setPlugins(new Interceptor[]{new MyFirstPlugin()});
- pagehelper

9， 多语句插入
- insert..,values(),values()

10. 批量保存
- ExecutorType.BATCH, 预编译SQL一次，发多次参数
- SqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) 

11. 存储过程
-  SQL Server只需要定义#{@id,mode=IN,javaType=java.lang.String,jdbcType=VARCHAR}，不用定义mode=OUT
- 支持exec和call两种方式

12. TypeHandler
- 枚举 
    - org.apache.ibatis.type.EnumOrdinalTypeHandler
    - org.apache.ibatis.type.EnumTypeHandler
- 自定义类型处理器 org.apache.ibatis.type.BaseTypeHandler
    - factoryBean.setTypeHandlers();
    - 插入：#{id, typeHandler=全类名}
    - 查询：<resultMap><result typeHandler=XXX>