package com.sonic.sample.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.lang.IgniteCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * WebController
 *
 * @author Sonic
 * @since 2020/5/28
 */
@Slf4j
@RestController
public class WebController{

    @Autowired
    private Ignite ignite;

    @GetMapping("/compute")
    public void compute() {
        Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

        // Iterate through all the words in the sentence and create Callable jobs.
        for (final String word : "Count characters using callable".split(" "))
            calls.add(word::length);

        // Execute collection of Callables on the grid.
        Collection<Integer> res = ignite.compute().call(calls);

        // Add up all the results.
        int sum = res.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum);
        log.info("Total number of characters is '" + sum + "'.");
    }
}

