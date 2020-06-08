package com.sonic.demo.config;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.jta.atomikos.AtomikosConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;

/**
 * JmsConfig
 *
 * @author Sonic
 * @since 2020/6/6
 */
@EnableJms
@Configuration
public class JmsConfig {

//    @Bean
//    public PlatformTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
//        return new JmsTransactionManager(connectionFactory);
//    }

//    @Bean("amQueue")
//    public ActiveMQQueue activeMQQueue() {
//        ActiveMQQueue activeMQQueue = new ActiveMQQueue("AMQueue");
//        return activeMQQueue;
//    }

    @Bean("activeMQXAConnectionFactory")
    ActiveMQXAConnectionFactory activeMQXAConnectionFactory() {
        ActiveMQXAConnectionFactory mqxaConnectionFactory = new ActiveMQXAConnectionFactory();
        mqxaConnectionFactory.setBrokerURL("tcp://localhost:61616");
        mqxaConnectionFactory.setUserName("admin");
        mqxaConnectionFactory.setPassword("admin");
        return mqxaConnectionFactory;
    }

    @Bean("xaAtomikosConnectionFactory")
    @Primary
    public ConnectionFactory connectionFactory(@Qualifier("activeMQXAConnectionFactory") XAConnectionFactory xaConnectionFactory) {
        AtomikosConnectionFactoryBean atomikosConnectionFactoryBean = new AtomikosConnectionFactoryBean();
        atomikosConnectionFactoryBean.setUniqueResourceName("xaMQ");
//        atomikosConnectionFactoryBean.setLocalTransactionMode(true);
        atomikosConnectionFactoryBean.setXaConnectionFactory(xaConnectionFactory);
        atomikosConnectionFactoryBean.setMaxPoolSize(10);
        atomikosConnectionFactoryBean.setPoolSize(10);
        return atomikosConnectionFactoryBean;
    }

    @Bean
    public JmsTemplate jmsTemplate(@Qualifier("xaAtomikosConnectionFactory") ConnectionFactory connectionFactory
//            , @Qualifier("amQueue") ActiveMQQueue activeMQQueue
    ) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setReceiveTimeout(10000L);
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setSessionTransacted(true);
//        jmsTemplate.setDefaultDestination(activeMQQueue);
        return jmsTemplate;
    }

    @Bean("jmsListenerContainerFactory")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(@Qualifier("xaAtomikosConnectionFactory") ConnectionFactory connectionFactory,
//                                                                      @Qualifier("mysqlTransactionManager") DataSourceTransactionManager transactionManager,
                                                                      DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setReceiveTimeout(10000L);
        factory.setSessionTransacted(true);
//        factory.setTransactionManager(transactionManager);
        return factory;
    }

//    @Bean("jmsTransactionManager")
//    public JmsTransactionManager jmsTransactionManager(@Qualifier("xaAtomikosConnectionFactory") ConnectionFactory cf) {
//        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager(cf);
//        return jmsTransactionManager;
//    }

//    @Bean
//    public DefaultMessageListenerContainer messageListenerContainer(@Qualifier("mysqlTransactionManager") DataSourceTransactionManager transactionManager,
//                                                                    @Qualifier("xaAtomikosConnectionFactory") ConnectionFactory connectionFactory
//                                                                    ) {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setTransactionManager(transactionManager);
//        container.setConnectionFactory(connectionFactory);
//        container.setReceiveTimeout(10000L);
//        container.setSessionTransacted(true);
//        container.setDestinationName("user:msg:new");
//        return container;
//    }



}
