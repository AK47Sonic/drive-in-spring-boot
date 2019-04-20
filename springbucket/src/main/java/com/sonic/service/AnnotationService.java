package com.sonic.service;

import com.sonic.annotation.AopAction;
import org.springframework.stereotype.Service;

/**
 * HelloWorldService
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Service
public class AnnotationService {

    @AopAction("Annotation Intercept")
    public String getHelloWorld(){
        return "AnnotationService: Hello World";
    }
}
