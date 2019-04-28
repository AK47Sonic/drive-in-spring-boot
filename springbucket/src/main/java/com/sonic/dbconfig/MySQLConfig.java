package com.sonic.dbconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MySQLConfig
 *
 * @auther Sonic
 * @since 2018/12/29
 */
@ConfigurationProperties(prefix = "mybatis")
//@MapperScan(basePackages = "com.sonic.mapper", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@MapperScan(basePackages = "com.sonic.mapper", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
@Configuration
public class MySQLConfig {

    private static final Logger logger = LoggerFactory.getLogger(MySQLConfig.class);

    public List<String> mapperLocationList;

    public String getTypeHandlersPackage() {
        return typeHandlersPackage;
    }

    public void setTypeHandlersPackage(String typeHandlersPackage) {
        this.typeHandlersPackage = typeHandlersPackage;
    }

    private String typeHandlersPackage;

    public void setMapperLocationList(List<String> mapperLocationList) {
        this.mapperLocationList = mapperLocationList;
    }

    public List<String> getMapperLocationList() {
        return mapperLocationList;
    }

    @ConfigurationProperties(prefix = "datasource.primary")
    @Bean("mysqlDataSource")
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }

    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration myBatisConfig = new org.apache.ibatis.session.Configuration();
        myBatisConfig.setMapUnderscoreToCamelCase(true);
        myBatisConfig.setJdbcTypeForNull(JdbcType.NULL);

        //Enable POJO lazy loading
        myBatisConfig.setLazyLoadingEnabled(true);
        myBatisConfig.setAggressiveLazyLoading(false);
        myBatisConfig.setCacheEnabled(true);

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(myBatisConfig);

        factoryBean.setTypeAliasesPackage("com.sonic.bean");
        factoryBean.setTypeHandlersPackage(typeHandlersPackage);

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = mapperLocationList.stream().map(el -> {
            List<Resource> rs = new ArrayList<>();
            try {
                rs.addAll(Arrays.asList(patternResolver.getResources(el)));
            } catch (IOException e) {
                logger.error("mapperLocationList: " + e);
                throw new RuntimeException(e);
            }
            return rs;
        }).flatMap(ls -> ls.stream()).toArray(Resource[]::new);
        factoryBean.setMapperLocations(resources);

        return factoryBean.getObject();
    }

    @Bean("mysqlTransactionManger")
    public PlatformTransactionManager getTransactionManger(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mysqlSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("sonicMyBatisCursorItemReader")
    public MyBatisCursorItemReader myBatisCursorItemReader(@Autowired SqlSessionFactory sqlSessionFactory){
        MyBatisCursorItemReader myBatisCursorItemReader = new MyBatisCursorItemReader();
        myBatisCursorItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisCursorItemReader.setQueryId("com.sonic.mapper.CoffeeMapper.queryCoffeeById");
        return myBatisCursorItemReader;
    }

}
