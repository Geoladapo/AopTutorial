package com.oladapo.Aop.Demo.dao;

import com.oladapo.Aop.Demo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my DB WORK: ADDING AN ACCOUNT");
    }
}
