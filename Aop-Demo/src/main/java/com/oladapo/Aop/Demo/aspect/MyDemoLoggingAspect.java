package com.oladapo.Aop.Demo.aspect;

import com.oladapo.Aop.Demo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {


    @Before("com.oladapo.Aop.Demo.aspect.PointCutAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n===>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display the arguments to the method
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {
                Account theAccount = (Account) tempArg;
                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.oladapo.Aop.Demo.dao.AccountDao.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n===>>> result is: " + result);

        // let's post-process the result
        // convert the account name to uppercase
        convertAccountNameToUpperCase(result);
        System.out.println("\n===>>> result is: " + result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        for (Account resultAccount : result) {
            String theUpperName = resultAccount.getName().toUpperCase();
            resultAccount.setName(theUpperName);
        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.oladapo.Aop.Demo.dao.AccountDao.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @AfterThrowing on method: " + method);

        System.out.println("\n===>>> The exception is: " + theExc);
    }

    @After("execution(* com.oladapo.Aop.Demo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @After (Finally) on method: " + method);
    }

    @Around("execution(* com.oladapo.Aop.Demo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {


        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        //now, let's execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            throw exc;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n===>>> Duration: " + duration / 1000.0 + "s");
        return result;
    }
}
