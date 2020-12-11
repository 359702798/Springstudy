package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类,它包含了,开启事务,回滚事务和释放连接
 */
@Component("transactionManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}
    /**
     * 开启事务
     */
   // @Before("pt1()")
    public void beginTransaction() {

        try {
            System.out.println("开启了事务");
            connectionUtils.getThreadConnection().setAutoCommit(false);

        } catch (Exception e) {

            throw new RuntimeException();

        }


    }
    /**
     * 资源的释放
     */
    //@After("pt1()")
    public void release() {

        try {
            System.out.println("资源释放了");
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();


        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    /**
     * 提交事务
     */
    //@AfterReturning("pt1()")
    public void commit() {
        try {
            System.out.println("提交了事务");
            connectionUtils.getThreadConnection().commit();

        } catch (Exception e) {

            throw new RuntimeException();

        }


    }

    /**
     * 事务的回滚
     */
    //@AfterThrowing("pt1()")
    public void rollback() {
        try {
            System.out.println("回滚了事务");
            connectionUtils.getThreadConnection().rollback();

        } catch (Exception e) {

            throw new RuntimeException();

        }


    }


    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return  rtValue;

        }catch (Throwable e){
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            this.release();
        }
    }

}


