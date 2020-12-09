package com.itheima.utils;


import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类,它提供了公共的代码
 */
public class Logger {
    /**
     * 前置通知
     */
    public void beforeprintLog(){
        System.out.println("前置通知Logger类中的beforeprintLog方法开始记录日志了。。。");


    }

    /**
     * 后置通知
     */
    public void afterReturningprintLog(){
        System.out.println("后置通知Logger类中的afterReturningprintLog方法开始记录日志了。。。");


    }

    /**
     * 异常通知
     */
    public void afterThrowingprintLog(){
        System.out.println("异常通知Logger类中的afterThrowingprintLog方法开始记录日志了。。。");


    }

    /**
     * 最终通知
     */
    public void afrterprintLog(){
        System.out.println("最终通知Logger类中的afrterprintLog方法开始记录日志了。。。");


    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */

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
