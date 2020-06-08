package com.sonic.demo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * TransactionManagerConfig
 *
 * @author Sonic
 * @since 2020/6/6
 */
@Configuration
public class TransactionManagerConfig {

    @Bean(name = "xaTransactionManager")
    @Primary
    public JtaTransactionManager xaTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        UserTransaction userTransaction = new UserTransactionImp();
        try {
            userTransaction.setTransactionTimeout(300);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

}
