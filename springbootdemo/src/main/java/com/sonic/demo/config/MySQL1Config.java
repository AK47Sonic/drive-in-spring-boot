package com.sonic.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MySQL1Config
 *
 * @auther Sonic
 * @since 2018/12/29
 */
@ConfigurationProperties(prefix = "mybatis.mysql1")
@MapperScan(basePackages = "com.sonic.demo.mapper.mysql1", sqlSessionTemplateRef = "mysql1SqlSessionTemplate")
@Configuration
public class MySQL1Config {
    private static final Logger logger = LoggerFactory.getLogger(MySQL1Config.class);

    private String userName;
    private String password;
    private String url;
    private String driverClassName;
    public List<String> mapperLocationList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMapperLocationList(List<String> mapperLocationList) {
        this.mapperLocationList = mapperLocationList;
    }

    public List<String> getMapperLocationList() {
        return mapperLocationList;
    }

    @Bean("mysql1DataSource")
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setDriverClassName(driverClassName);
        return hikariDataSource;
    }

    @Bean("mysql1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql1DataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration myBatisConfig = new org.apache.ibatis.session.Configuration();
        myBatisConfig.setMapUnderscoreToCamelCase(true);
        myBatisConfig.setJdbcTypeForNull(JdbcType.NULL);

        //Enable POJO lazy loading
        myBatisConfig.setLazyLoadingEnabled(true);
        myBatisConfig.setAggressiveLazyLoading(false);
        myBatisConfig.setCacheEnabled(true);
        myBatisConfig.setDefaultStatementTimeout(10000);
        myBatisConfig.setUseColumnLabel(true);
        myBatisConfig.setUseActualParamName(true);
//        myBatisConfig.setJdbcTypeForNull(JdbcType.NULL); // Oracle


        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(myBatisConfig);

        factoryBean.setTypeAliasesPackage("com.sonic.demo.domain");

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

    @Bean("mysql1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysql1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
