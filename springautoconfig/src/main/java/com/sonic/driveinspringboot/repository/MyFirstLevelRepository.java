package com.sonic.driveinspringboot.repository;

import com.sonic.driveinspringboot.annotation.FirstLevelRepository;
import com.sonic.driveinspringboot.annotation.SecondLevelRepository;

/** {@link FirstLevelRepository}
 * Create by Sonic on 2018/11/25
 */
@SecondLevelRepository(value = "myFirstLevelRepository") //bean name
//@Component(value = "myFirstLevelRepository")
public class MyFirstLevelRepository {
}
