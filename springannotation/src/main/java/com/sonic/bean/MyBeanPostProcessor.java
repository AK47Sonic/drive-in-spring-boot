package com.sonic.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器：初始化前后进行处理
 * 将后置处理器加入到容器中
 *
 * BeanPostProcessor的原理
 * 遍历得到容器中所有的BeanPostProcessor：挨个执行，一旦方法返回null，
 * 跳出for循环，不会执行后面的BeanPostProcessor
 * createBeanInstance(beanName, mbd, args);
 * populateBean(beanName, mbd, instanceWrapper);//给Bean进行属性赋值 set方法
 * initializeBean(beanName, exposedObject, mbd);{
 *   applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *   invokeInitMethods(beanName, wrappedBean, mbd); 执行自定义初始化，包括@PostConstruct,InitializingBean等
 *   applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 * @auther Sonic
 * @since 2018/12/16
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization.." + beanName + "===》" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization.." + beanName + "===》" + bean);
        return bean;
    }
}
