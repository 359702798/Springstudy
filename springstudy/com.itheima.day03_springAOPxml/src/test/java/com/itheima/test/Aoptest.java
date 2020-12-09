package com.itheima.test;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aoptest {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");

        //2.获取对象
        IAccountService accountService=(IAccountService) ac.getBean("accountservice");
        //3执行方法
        accountService.saveAccount();

    }
}
