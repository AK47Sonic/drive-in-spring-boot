package com.sonic.driveinspringboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Java8 lambda 实现 {@link CalculateService}
 * Create by Sonic on 2018/11/25
 */
@Profile("Java8")
@Service
public class Java8CalculateService implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("-------------------Java 8-------------------------");
        int sum = Stream.of(values).reduce(0, Integer::sum);
        return sum;
    }

    public static void main(String[] args) {
        Java8CalculateService java8CalculateService = new Java8CalculateService();
        System.out.println("Java 8 : " + java8CalculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
