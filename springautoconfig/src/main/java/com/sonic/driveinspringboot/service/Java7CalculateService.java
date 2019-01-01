package com.sonic.driveinspringboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Java7 for 循环实现 {@link CalculateService}
 * Create by Sonic on 2018/11/25
 */
@Profile("Java7")
@Service
public class Java7CalculateService implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("-------------------Java 7-------------------------");
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];

        }
        return sum;
    }

    public static void main(String[] args) {
        Java7CalculateService java7CalculateService = new Java7CalculateService();
        System.out.println("Java 7 : " + java7CalculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
