package com.sonic;

import com.sonic.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * SpringBootAppTest
 *
 * @author Sonic
 * @since 2019/5/5
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource(value = {"classpath:externalized-config.properties"})
@SpringBootTest(classes = {ClientApplication.class})
public class SpringBootAppTest {

    private static Logger logger = LoggerFactory.getLogger(SpringBootAppTest.class);

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testPerson() {

        Person person = applicationContext.getBean( Person.class);

        logger.info("person: {}", person);

        logger.info("myDog: {}", applicationContext.containsBean("myDog"));

        logger.info("beans: {}", Arrays.toString(applicationContext.getBeanDefinitionNames()));

    }

}
