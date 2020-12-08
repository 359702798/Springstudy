package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService{
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private TransactionManager transactionManager;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();

            //2.执行操作
            //2.1.根据名称查出转出账户对象
            Account source=accountDao.findAccountByName(sourceName);
            //2.2根据名称查出转入账户对象
            Account  target=accountDao.findAccountByName(targetName);
            //2.3转出账户减钱
            source.setMoney(source.getMoney()-money);



            //2.4转出账户加钱
            target.setMoney(target.getMoney()+money);

            //2.5更新转出账户
            accountDao.updateAccount(source);

           //int i=1/0;
            //2.6更新转入账户
            accountDao.updateAccount(target);

            transactionManager.commit();




        }catch (Exception e){

           e.printStackTrace();

            transactionManager.rollback();
        }

        finally {

            transactionManager.release();

        }




    }



}
