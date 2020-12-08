package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        final Producer producer=new Producer();



/**
 * 1.创建代理对象
 * 使用proxy类中的newProxyInstance方法
 * 2.创建代理对象的要求
 * 被代理类至少实现一个接口,没有则不能使用
 * 3.newProxyInstance方法的参数
 * Loader：类加载器
 * 用于加载动态代理对象的字节码,写的是和被代理对象相同的类加载器。固定写法
 * producer.getClass().getClassLoader()
 *
 * Class[]:字节码数组
 * 让代理对象和被代理对象有相同的方法。
 * producer.getClass().getInterfaces()
 *
 * invocationHandler:
 * 代理内容,一般情况下写一个该接口的实现类,一般是匿名内部类,此接口的实现类谁用谁写。
 * new InvocationHandler()
 *
 *
 */
        IProducer proxyProducer=(IProducer) Proxy.newProxyInstance(
        producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
       new InvocationHandler() {
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue=null;
                        Float money=(Float)args[0];

                        if("saleProduct".equals(method.getName())){

                            returnValue=method.invoke(producer,money*0.8f);


                        }
                        return returnValue;

                    }
                }
        );

        System.out.println("代理商收取20%的费用");
        proxyProducer.saleProduct(10000f);


    }
}
