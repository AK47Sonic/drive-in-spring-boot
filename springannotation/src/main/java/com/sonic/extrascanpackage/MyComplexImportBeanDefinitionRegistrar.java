package com.sonic.extrascanpackage;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MyComplexImportBeanDefinitionRegistrar
 *
 * @author Sonic
 * @since 2019/2/17
 */
public class MyComplexImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Computer.class);
        genericBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "Sky-Computer");
        genericBeanDefinition.setRole(BeanDefinition.ROLE_APPLICATION);
        registry.registerBeanDefinition("skyComputer", genericBeanDefinition);
    }
}
