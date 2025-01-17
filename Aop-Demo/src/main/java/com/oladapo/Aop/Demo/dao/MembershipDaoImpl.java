package com.oladapo.Aop.Demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao {
    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + ": Doing my DB WORK: ADDING A MEMBERSHIP ACCOUNT");
        return true;
    }
}
