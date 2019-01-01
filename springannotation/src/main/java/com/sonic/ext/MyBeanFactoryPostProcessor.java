package com.sonic.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * MyBeanFactoryPostProcessor
 *
 * @auther Sonic
 * @since 2018/12/23
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...count: " + beanFactory.getBeanDefinitionCount());
//        Arrays.asList(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
