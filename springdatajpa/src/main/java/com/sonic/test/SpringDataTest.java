package com.sonic.test;

import com.sonic.domain.Person;
import com.sonic.repository.PersonRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * SpringDataTest
 *
 * @author Sonic
 * @since 2020/3/1
 */
public class SpringDataTest {

    private ApplicationContext ctx = null;
    private PersonRepository personRepository;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        personRepository = ctx.getBean(PersonRepository.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        HikariDataSource dataSourceHikari = ctx.getBean("dataSourceHikari", HikariDataSource.class);
        System.out.println(dataSourceHikari.getConnection());
    }

    @Test
    public void testSpringData() {
        Person person = personRepository.getByLastName("Sonic");
        System.out.println("person: " + person);
    }

    @Test
    public void testJPA() {

    }

    @Test
    public void testKeyWords() {
        List<Person> ps = personRepository.getByLastNameStartingWithAndIdLessThan("S", 10);
        System.out.println(ps);

        List<Person> personList = personRepository.getByEmailInOrBirthLessThan(Arrays.asList("Sonic@qq.com", "SYY@qq.com"), new Date());
        System.out.println(personList);

    }

    @Test
    public void testJoin() {
//        List<Person> personList = personRepository.getByAddressIdGreaterThan(1);
//        System.out.println(personList);

        List<Person> personList2 = personRepository.getByAddress_ProvinceEquals("Shanghai");
        System.out.println(personList2);
    }

}
