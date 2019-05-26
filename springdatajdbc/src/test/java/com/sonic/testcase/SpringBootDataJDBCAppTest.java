package com.sonic.testcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SpringBootDataJDBCAppTest
 *
 * @author Sonic
 * @since 2019/5/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.sonic.bootstrap.SpringBootDataJDBCBootstrap.class})
@ActiveProfiles("test")
public class SpringBootDataJDBCAppTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootDataJDBCAppTest.class);

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        logger.info("dataSource: {}", dataSource.getClass());
        Connection connection = dataSource.getConnection();
        logger.info("connection: {}", connection);
        connection.close();
    }
}
