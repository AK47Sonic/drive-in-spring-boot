package com.sonic;

import com.sonic.service.DeclarativeTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DeclarativeTransactionApplication
 *
 * @author Sonic
 * @since 2019/4/21
 */
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class DeclarativeTransactionApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DeclarativeTransactionApplication.class);

    @Autowired
    private DeclarativeTransactionService declarativeTransactionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder(DeclarativeTransactionApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        // Step 1
//        declarativeTransactionService.insertRecord();
//        logger.info("AAA {}",
//                jdbcTemplate
//                        .queryForObject("SELECT COUNT(*) FROM student WHERE sname='AAA'", Long.class));
//        // Step 2
//        try {
//            declarativeTransactionService.insertThenRollback();
//        } catch (Exception e) {
//            logger.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM student WHERE sname='BBB'", Long.class));
//        }
//        // Step 3
//        try {
//            declarativeTransactionService.invokeInsertThenRollbackWrong();
//        } catch (Exception e) {
//            logger.info(e.getMessage(), e);
//            logger.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM student WHERE sname='BBB'", Long.class));
//        }
//
//        // Step 4
//        try {
//            declarativeTransactionService.invokeInsertThenRollback();
//        } catch (Exception e) {
//            logger.info(e.getMessage(), e);
//            logger.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM student WHERE sname='BBB'", Long.class));
//        }

        // Step 5
        try {
            declarativeTransactionService.invokeInsertThenRollbackByAopContext();
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            logger.info("BBB {}",
                    jdbcTemplate
                            .queryForObject("SELECT COUNT(*) FROM student WHERE sname='BBB'", Long.class));
        }


    }
}
