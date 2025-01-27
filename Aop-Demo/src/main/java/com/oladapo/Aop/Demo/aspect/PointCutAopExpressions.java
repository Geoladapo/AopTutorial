package com.oladapo.Aop.Demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutAopExpressions {

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.set*(..))")
    public void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }

}
