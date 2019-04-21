package com.sonic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * ProgrammaticTransactionApplication
 *
 * @author Sonic
 * @since 2019/4/21
 */
@SpringBootApplication
public class ProgrammaticTransactionApplication
//        implements CommandLineRunner
{

    private Logger logger = LoggerFactory.getLogger(ProgrammaticTransactionApplication.class);

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProgrammaticTransactionApplication.class).web(WebApplicationType.NONE).run(args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        logger.info("COUNT BEFORE TRANSACTION: {}", getCount());
//        transactionTemplate.execute((status) -> {
//            jdbcTemplate.execute("INSERT INTO student (id, sname) VALUES (2, 'Saber')");
//            logger.info("COUNT IN TRANSACTION: {}", getCount());
//            status.setRollbackOnly();
//            return 1;
//        });
//        logger.info("COUNT AFTER TRANSACTION: {}", getCount());
//    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT count(1) as CNT FROM student").get(0).get("CNT");
    }
}
