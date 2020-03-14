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
            - 或使用分步查询  <association property="department" select="com.sonic.mapper.DepartmentMapper.getDeptById" column="{id=d_id}">
            


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
- setLazyLoadingEnabled(true), setAggressiveLazyLoading(false) 分步查询，开启懒加载

4. Annotation
- @Alias("别名") 在XML中,可使用别名代替全类名，覆盖setTypeAliasesPackage的设置
- @Select 直接在Mapper接口方法上写sql，来省略Mapper.xml
- @MapperScan 指定Mapper接口所在的包，批量注册
- @Mapper 单一注册Mapper接口
- 


