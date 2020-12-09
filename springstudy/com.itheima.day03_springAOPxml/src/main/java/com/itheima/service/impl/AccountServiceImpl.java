package com.itheima.service.impl;

import com.itheima.service.IAccountService;

public class AccountServiceImpl  implements IAccountService {


    public void saveAccount() {
        System.out.println("账户保存了");
    }

    public void updateAccount(int i) {
        System.out.println("账户更新了");
    }

    public int delete() {
        System.out.println("账户删除了");
        return  0;
    }
}
