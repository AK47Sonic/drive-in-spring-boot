package com.sonic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 *
 * @auther Sonic
 * @since 2018/12/22
 */
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1. 在本类引用
    //2. 其他的切面引用
    @Pointcut("execution(public int com.sonic.aop.MathCalculator.*(..))")
    public void pointCut(){

    }

    //@Before在目标方法之前切入：切入点表达式（指定在哪个方法切入）
    @Before("com.sonic.aop.LogAspects.pointCut()")
    public void logStart(){
        System.out.println("Start div....");
    }

    @After("com.sonic.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println(signature.getName() + ", " + Arrays.asList(args) + ", End div....");
    }

    @AfterReturning(value = "com.sonic.aop.LogAspects.pointCut()", returning = "result")
    //JoinPoint一定要出现在参数列表的第一位
    public void logReturn(JoinPoint joinPoint, Object result){
        System.out.println(joinPoint.getSignature().getName() + ", Normal return div.... : " + result);
    }

    @AfterThrowing(value = "com.sonic.aop.LogAspects.pointCut()", throwing = "throwable")
    public void logException(JoinPoint joinPoint, Throwable throwable){
        System.out.println(joinPoint.getSignature().getName() + ", Exception return div...: " + throwable);
    }

}
