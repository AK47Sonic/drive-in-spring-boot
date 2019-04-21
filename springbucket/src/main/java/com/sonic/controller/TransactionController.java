package com.sonic.controller;

import com.sonic.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionController
 *
 * @author Sonic
 * @since 2019/4/20
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/insert")
    public int insertData(){
        return transactionService.insertStudent();
    }

}
