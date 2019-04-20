package com.sonic;

import com.sonic.service.AnnotationService;
import com.sonic.service.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ApplicationBootstrap
 *
 * @author Sonic
 * @since 2019/4/20
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ApplicationBootstrap {

    private static Logger logger = LoggerFactory.getLogger(ApplicationBootstrap.class);
    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ApplicationBootstrap.class).run(args);
        ServiceInterface methodService = (ServiceInterface) applicationContext.getBean("methodService");
        AnnotationService annotationService = (AnnotationService) applicationContext.getBean("annotationService");
        logger.info("annotationService:" + annotationService);
        logger.info("annotationService:" + annotationService.getClass());
        logger.info("annotationService:" + annotationService.getClass().getSimpleName());
        logger.info("methodService:" + methodService);
        logger.info("methodService:" + methodService.getClass());
        logger.info("methodService:" + methodService.getClass().getSimpleName());

    }
}
