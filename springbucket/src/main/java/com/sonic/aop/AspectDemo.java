package com.sonic.aop;

import com.sonic.annotation.AopAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AspectDemo
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Aspect
@Component
public class AspectDemo {

    @Pointcut("@annotation(com.sonic.annotation.AopAction)")
    public void annotationPointCut() {
    }

    @Pointcut("execution(* com.sonic.service.MethodService.getHelloWorld(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //拦截了方法
        Method method = signature.getMethod();
        //直接获取方法名字
        System.out.println("方法规则式拦截: " + method.getName());
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //从切面中获取当前方法
        Method method = signature.getMethod();
        //得到了方,提取出他的注解
        AopAction action = method.getAnnotation(AopAction.class);
        //输出
        System.out.println("注解式拦截: " + action.value());
    }

}
