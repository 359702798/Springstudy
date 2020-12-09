package com.itheima.service;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     *
     * @param i
     */
    void updateAccount(int i);

    /**
     *
     */
    int delete();

}
