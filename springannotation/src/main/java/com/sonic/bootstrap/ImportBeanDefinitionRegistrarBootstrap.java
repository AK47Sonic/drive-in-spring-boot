package com.sonic.bootstrap;

import com.sonic.extrascanpackage.Computer;
import com.sonic.extrascanpackage.MyComplexImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * ImportBeanDefinitionRegistrarBootstrap
 *
 * @author Sonic
 * @since 2019/2/17
 */

@SpringBootApplication(scanBasePackages = "com.sonic.extrascanpackage")
@Import(value = {MyComplexImportBeanDefinitionRegistrar.class})
public class ImportBeanDefinitionRegistrarBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ImportBeanDefinitionRegistrarBootstrap.class, args);
//        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        Computer computer = (Computer) applicationContext.getBean("skyComputer",Computer.class);
        System.out.println(computer);
    }

}
