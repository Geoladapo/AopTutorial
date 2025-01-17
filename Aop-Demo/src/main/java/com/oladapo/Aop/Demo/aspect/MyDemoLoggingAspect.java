package com.oladapo.Aop.Demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.get*(..))")
    private void getter(){}

    @Pointcut("execution(* com.oladapo.Aop.Demo.dao.*.set*(..))")
    private void setter(){}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}

    @Before ("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n===>>> Executing @Before advice on addAccount()");
    }

    @Before ("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n===>>> Performing API analytics");
    }
}
