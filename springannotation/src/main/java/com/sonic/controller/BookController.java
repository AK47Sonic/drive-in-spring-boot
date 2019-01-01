package com.sonic.controller;

import com.sonic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller
 *
 * @auther Sonic
 * @since 2018/12/15
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
