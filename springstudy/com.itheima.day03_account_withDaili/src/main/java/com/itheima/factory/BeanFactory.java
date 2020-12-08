package com.itheima.factory;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component("beanFactory")
public class BeanFactory {
    public final  void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    @Qualifier("accountServiceDai")
    private IAccountService accountService;
    @Autowired
    private TransactionManager transactionManager;



    /**
     * 获取Service代理对象
     */
    @Bean("proIAccountService")
    public  IAccountService getAccountService() {

      return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtvalue =null;

                        try {
                            //1.开启事务
                            transactionManager.beginTransaction();
                            //2.执行操作
                            rtvalue = method.invoke(accountService, args);
                            //3.提交事务
                            transactionManager.commit();

                            return rtvalue;


                        } catch (Exception e) {

                            e.printStackTrace();
                            transactionManager.rollback();
                            throw  new RuntimeException(e);
                        } finally {

                            transactionManager.release();

                        }


                    }



                });


    }


}
