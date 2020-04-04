package com.sonic.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * ApplicationRunnerBootstrap
 *
 * @author Sonic
 * @since 2019/4/2
 */
@SpringBootApplication
@RestController
public class ApplicationRunnerBootstrap implements ApplicationRunner {

    private String[] values;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunnerBootstrap.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        values = args.getSourceArgs();
        System.out.println(args.getSourceArgs());
        System.out.println(args.getOptionNames());
        String env = System.getProperty("env");
        String var = System.getenv("var_env");
        System.out.println("env:" + env);
        System.out.println("var:" + var);
//        System.out.println(args.getOptionValues());
    }

    @RequestMapping
    public String show() {
        return Arrays.toString(values);
    }

}
