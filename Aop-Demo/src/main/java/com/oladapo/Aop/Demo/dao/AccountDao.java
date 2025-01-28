package com.oladapo.Aop.Demo.dao;

import com.oladapo.Aop.Demo.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAccounts();
    List<Account> findAccounts(boolean tripWire);
    void addAccount(Account theAccount, boolean vipFlag);
    public String getName();
    public void setName(String name);
    public String getServiceCode();
    public void setServiceCode(String serviceCode);
}
