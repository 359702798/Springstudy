package com.itheima.cglib;



import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;


public class Client {

    public static void main(String[] args) {
        final Producer producer=new Producer();

      

/**
 * 基于子类的动态代理
 * 1.创建代理对象
 *
 * 2.创建代理对象的要求
 * 被代理类至少实现一个接口,没有则不能使用
 *
 * 3.newProxyInstance方法的参数
 * Class字节码:
 * 用于指定被代理对象的字节码
 *producer.getClass()
 *
 * Callback:用于提供被增强的代码
 * 代理内容,一般情况下写一个该接口的实现类,一般是匿名内部类,此接口的实现类谁用谁写。
 * 我们一般用该接口的子接口实现类:MethodInterceptor
 *
 * new MethodInterceptor
 *
 */


      Producer cglibProdecer=(Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {

            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param proxy
             * @param method
             * @param args
             * @param methodProxy
             * @return
             * @throws Throwable
             */
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue=null;
        Float money=(Float)args[0];

        if("saleProduct".equals(method.getName())){

            returnValue=method.invoke(producer,money*0.8f);


        }
        return returnValue;

    }});
      cglibProdecer.afterService(10000f);
      cglibProdecer.saleProduct(12000f);



    }
}
