package com.sonic.driveinspringboot.bootstrap;

import com.sonic.driveinspringboot.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link CalculateService}
 * Create by Sonic on 2018/11/25
 */
@SpringBootApplication(scanBasePackages = "com.sonic.driveinspringboot.service")
public class CalculateServiceBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
//                .profiles("Java8") //相当于-Dspring.profiles.active=Java7
                .run(args);
        CalculateService calculateService = cac.getBean(CalculateService.class);
        System.out.println(" calculateService sum : " +
                calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        cac.close();

    }
}
