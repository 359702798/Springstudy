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
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private TransactionManager transactionManager;


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

        //1.执行操作
        //1.1.根据名称查出转出账户对象
        Account source = accountDao.findAccountByName(sourceName);
        //1.2根据名称查出转入账户对象
        Account target = accountDao.findAccountByName(targetName);
        //1.3转出账户减钱
        source.setMoney(source.getMoney() - money);


        //1.4转出账户加钱
        target.setMoney(target.getMoney() + money);

        //1.5更新转出账户
        accountDao.updateAccount(source);

        int i = 1 / 0;
        //1.6更新转入账户
        accountDao.updateAccount(target);


    }


}
