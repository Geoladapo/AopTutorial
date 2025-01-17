package com.oladapo.Aop.Demo;

import com.oladapo.Aop.Demo.dao.AccountDao;
import com.oladapo.Aop.Demo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDao theAccountDAO,
                                               MembershipDao theMembershipDAO) {
        return runner -> {
            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDao theAccountDAO,
                                     MembershipDao theMembershipDAO) {

        Account account = new Account();
        theAccountDAO.addAccount(account, true);

        theMembershipDAO.addSillyMember();
    }
}
