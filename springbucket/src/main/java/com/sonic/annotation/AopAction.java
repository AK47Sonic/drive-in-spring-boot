package com.sonic.annotation;

import java.lang.annotation.*;

/**
 * AopAction
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopAction {
    String value();
}
