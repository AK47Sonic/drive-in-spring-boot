package com.sonic;

import com.sonic.exception.MySQLColumnNotMatchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ErrorCodeApplicationTests
 *
 * @author Sonic
 * @since 2019/4/21
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DeclarativeTransactionApplication.class})
public class ErrorCodeApplicationTests {

    private Logger logger = LoggerFactory.getLogger(ErrorCodeApplicationTests.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test(expected = MySQLColumnNotMatchException.class)
    public void testThrowingCustomException() {
        try {
            jdbcTemplate.execute("INSERT INTO student (sname) VALUES (4, 'BBB')");
            jdbcTemplate.execute("INSERT INTO student (sname) VALUES (4, 'BBB')");
        } catch (DataAccessException e) {
            logger.info(e.getMessage(), e);
            throw e;
        }
    }
}
