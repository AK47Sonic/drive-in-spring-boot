package com.sonic.test;

import com.sonic.domain.Person;
import com.sonic.repository.PersonRepository;
import com.sonic.service.PersonService;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private PersonService personService;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        personRepository = ctx.getBean(PersonRepository.class);
        personService = ctx.getBean(PersonService.class);
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

    @Test
    public void testQueryAnnotation() {
        Person maxIdPerson = personRepository.getMaxIdPerson();
        System.out.println("maxIdPerson: " + maxIdPerson);
    }

    @Test
    public void testQueryAnnotationParam() {
        List<Person> personList = personRepository.testQueryAnnotationParams("Sonic", "Sonic@qq.com");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationParam2() {
        List<Person> personList = personRepository.testQueryAnnotationParams2("Sonic@qq.com", "Sonic");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationLikeParam() {
//        List<Person> personList = personRepository.testQueryAnnotationLikeParam("Son%", "Sonic%");
        List<Person> personList = personRepository.testQueryAnnotationLikeParam("Son", "Sonic");
        System.out.println(personList);
    }

    @Test
    public void testNativeQuery() {
        long count = personRepository.getTotalCount();
        System.out.println(count);
    }

    /**
     * JPQL使用{@code @Modifying}可支持update/delete, 但还需要事务支持
     */
    @Test
    public void testModifying() {
//        personRepository.updatePersonEmail(1, "mm@qq.com");
        personService.updatePersonEmail("mm@qq.com", 1);
    }

    @Test
    public void testCrudRepository() {
        List<Person> personList = new ArrayList<>();
        for (int i = 'a'; i < 'd'; i++) {
            Person person = new Person();
            person.setBirth(new Date());
            person.setEmail("hh@qq.com");
            personList.add(person);
        }
        personService.savePersons(personList);
    }

}
