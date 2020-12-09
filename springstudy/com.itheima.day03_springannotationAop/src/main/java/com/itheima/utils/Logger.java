package com.itheima.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类,它提供了公共的代码
 */
@Component("logger")
@Aspect
public class Logger {
    @Pointcut("execution( public void com.itheima.service.impl.AccountServiceImpl.saveAccount())")
    private void pt1(){};


    /**
     * 前置通知
     */
    //@Before("pt1()")
    public void beforeprintLog(){
        System.out.println("前置通知Logger类中的beforeprintLog方法开始记录日志了。。。");


    }

    /**
     * 后置通知
     */
    //@AfterReturning("pt1()")
    public void afterReturningprintLog(){
        System.out.println("后置通知Logger类中的afterReturningprintLog方法开始记录日志了。。。");


    }

    /**
     * 异常通知
     */
    //@AfterThrowing("pt1()")
    public void afterThrowingprintLog(){
        System.out.println("异常通知Logger类中的afterThrowingprintLog方法开始记录日志了。。。");


    }

    /**
     * 最终通知
     */
    //@After("pt1()")
    public void afrterprintLog(){
        System.out.println("最终通知Logger类中的afrterprintLog方法开始记录日志了。。。");


    }

    @Around("pt1()")
    public Object aroundprintLog(ProceedingJoinPoint pjp){

        Object rtvalue=null;
        try {
            Object[] args=pjp.getArgs();

            System.out.println("方法前执行的！。。");
            //执行切入点方法
            rtvalue= pjp.proceed(args);
            System.out.println("方法后执行的！。。");
            return  rtvalue;
        } catch (Throwable throwable) {
            System.out.println("方法异常执行的！。。");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("方法最终执行的！。。");
        }


    }


}
