package com.sonic.service;

import com.sonic.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service
 *
 * @auther Sonic
 * @since 2018/12/15
 */
@Service
public class BookService {

//    @Autowired//(required = false)
//    @Qualifier(value = "bookDao3")
    @Resource
    private BookDao bookDao3;

    public void print(){
        System.out.println(bookDao3);
    }

}
