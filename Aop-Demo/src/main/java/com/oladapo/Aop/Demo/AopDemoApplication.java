package com.oladapo.Aop.Demo;

import com.oladapo.Aop.Demo.dao.AccountDao;
import com.oladapo.Aop.Demo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDao theAccountDAO,
                                               MembershipDao theMembershipDAO) {
        return runner -> {
//            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
//            demoTheAfterReturningAdvice(theAccountDAO);
            demoTheAfterThrowingAdvice(theAccountDAO);
        };
    }

    private void demoTheAfterThrowingAdvice(AccountDao theAccountDAO) {
        List<Account> theAccounts = null;

        try {
            boolean tripWire = true;

            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program: ... caught exception: " + exc);
        }

        System.out.println("\n\nMain program: demoTheAfterThrowingAdvice");
        System.out.println("------");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDao theAccountDAO) {
        List<Account> theAccounts = theAccountDAO.findAccounts();

        System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
        System.out.println("------");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDao theAccountDAO,
                                     MembershipDao theMembershipDAO) {

        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");

        theAccountDAO.addAccount(account, true);
        theMembershipDAO.addSillyMember();

        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();
    }
}
