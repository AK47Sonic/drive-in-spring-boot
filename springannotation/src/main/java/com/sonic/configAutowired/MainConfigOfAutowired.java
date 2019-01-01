package com.sonic.configAutowired;

import com.sonic.bean.Car;
import com.sonic.bean.Color;
import com.sonic.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * MainConfigOfAutowired
 *
 * @auther Sonic
 * @since 2018/12/16
 */
@Profile("uat")
@Configuration
public class MainConfigOfAutowired {

    @Bean("bookDao2")
    @Primary
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    @Profile("dev")
    @Bean("bookDao3")
    public BookDao bookDao3() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("3");
        return bookDao;
    }

    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
