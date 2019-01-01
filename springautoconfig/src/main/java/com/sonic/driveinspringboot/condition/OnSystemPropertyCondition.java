package com.sonic.driveinspringboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * Create by Sonic on 2018/11/25
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attribute = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        System.out.println(attribute.get("name").toString());
        String propertyName = String.valueOf(attribute.get("name"));
        String propertyValue = String.valueOf(attribute.get("value"));

        String javaPropertyValue = System.getProperty(propertyName);
        return propertyValue.equals(javaPropertyValue);
    }
}
