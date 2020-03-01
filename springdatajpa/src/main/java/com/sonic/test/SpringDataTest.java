package com.sonic.test;

import com.sonic.domain.Person;
import com.sonic.repository.PersonRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * SpringDataTest
 *
 * @author Sonic
 * @since 2020/3/1
 */
public class SpringDataTest {

    private ApplicationContext ctx = null;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

    }

    @Test
    public void testDataSource() throws SQLException {
        HikariDataSource dataSourceHikari = ctx.getBean("dataSourceHikari", HikariDataSource.class);
        System.out.println(dataSourceHikari.getConnection());
    }

    @Test
    public void testSpringData() {
        PersonRepository personRepository = ctx.getBean(PersonRepository.class);
        Person person = personRepository.getByLastName("Sonic");
        System.out.println("person: " + person);
    }

    @Test
    public void testJPA() {

    }

}
