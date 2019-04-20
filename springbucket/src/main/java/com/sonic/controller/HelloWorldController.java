package com.sonic.controller;

import com.sonic.service.AnnotationService;
import com.sonic.service.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * HelloWorldController
 *
 * @author Sonic
 * @since 2019/4/20
 */
@RestController
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private AnnotationService annotationService;
    @Resource
    private ServiceInterface methodService;

    @GetMapping("/getMethod")
    public String getHelloWorldMethod(){

        logger.info("methodService: {}", methodService);
        return methodService.getHelloWorld();
    }

    @GetMapping("/getAnnotation")
    public String getHelloWorldAnnotation(){

        logger.info("annotationService: {}", annotationService);
        return annotationService.getHelloWorld();
    }

    @GetMapping("/getGoodBye")
    public String getGoodByeMethod(){

        logger.info("methodService: {}", methodService);
        return methodService.getGoodBye();
    }

}
