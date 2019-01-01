package com.sonic.condition;

import com.sonic.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @auther Sonic
 * @since 2018/12/16
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata: 当前类的注解信息
     * @param registry:               BeanDefinition注册类
     * @return void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //beanName要全类名，其实是调用了DefaultListableBeanFactory#beanDefinitionMap.containsKey(beanName)
        boolean redExist = registry.containsBeanDefinition("com.sonic.bean.Red");
        boolean blueExist = registry.containsBeanDefinition("com.sonic.bean.Blue");
        if (redExist && blueExist) {
            //注册一个Bean
            registry.registerBeanDefinition("rainBow", new RootBeanDefinition(RainBow.class));
        }
    }
}
