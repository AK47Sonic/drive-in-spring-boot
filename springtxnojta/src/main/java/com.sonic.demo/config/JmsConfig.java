package com.sonic.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

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

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL("tcp://localhost:61616");
        cf.setUserName("admin");
        cf.setPassword("admin");
        TransactionAwareConnectionFactoryProxy proxy = new TransactionAwareConnectionFactoryProxy();
        proxy.setTargetConnectionFactory(cf);
        proxy.setSynchedLocalTransactionAllowed(true);
        return proxy;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter jacksonJmsMessageConverter
    ) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setReceiveTimeout(10000L);
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        jmsTemplate.setSessionTransacted(true);
//        jmsTemplate.setDefaultDestination(activeMQQueue);
        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory cf,
                                                     PlatformTransactionManager transactionManager,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, cf);
        factory.setReceiveTimeout(10000L);
        factory.setTransactionManager(transactionManager);
        factory.setConcurrency("10");
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }


}
