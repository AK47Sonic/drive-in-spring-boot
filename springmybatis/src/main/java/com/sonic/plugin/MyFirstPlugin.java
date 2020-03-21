package com.sonic.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * MyFirstPlugin
 *
 * @author Sonic
 * @since 2020/3/21
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)})
public class MyFirstPlugin implements Interceptor {

    private String userName;
    private String password;

    public MyFirstPlugin(String userName, String password){
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin intercept: " + invocation.getMethod());
        Object resultSet = invocation.proceed();
        return resultSet;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin plugin: " + target);
        // target 是4大对象，返回动态代理
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyFirstPlugin properties: " + properties);
    }
}
