package com.oladapo.Aop.Demo.dao;

import com.oladapo.Aop.Demo.Account;

public interface AccountDao {
    void addAccount(Account theAccount, boolean vipFlag);
}
