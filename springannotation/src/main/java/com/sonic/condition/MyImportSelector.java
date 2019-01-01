package com.sonic.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MyImportSelector
 *
 * @auther Sonic
 * @since 2018/12/16
 */
public class MyImportSelector implements ImportSelector {

    //AnnotationMetadata: 当前标注@Import注解的类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //方法不要返回null值，会有空指针异常
        return new String[]{"com.sonic.bean.Blue", "com.sonic.bean.Yellow"};
    }
}
