package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */


@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:bean.xml")
public class AccountServiceTest {
           @Autowired
         private  IAccountService as=null;

    @Test
    public void testFindAll() {
       /*
       junit没有整合spring时获取容器和对象的方式
       ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
         IAccountService as=ac.getBean("accountService",IAccountService.class);
       */
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {

        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {

        Account account = new Account();
        account.setName("何大川");
        account.setMoney(1000000f);
        //3.执行方法
        as.saveAccount(account);

    }

    @Test
    public void testUpdate() {

        //3.执行方法
        Account account = as.findAccountById(4);
        account.setMoney(23456f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {

        //3.执行方法
        as.deleteAccount(4);
    }
}