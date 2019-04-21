package com.sonic.service;

import com.sonic.exception.RollbackException;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DeclarativeTransactionServiceImpl
 *
 * @author Sonic
 * @since 2019/4/21
 */
@Service
public class DeclarativeTransactionServiceImpl implements DeclarativeTransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DeclarativeTransactionService declarativeTransactionService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO student (id, sname) VALUES (3,'AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO student (id, sname) VALUES (4, 'BBB')");
        throw new RollbackException("RollbackException");
    }

    @Override
    public void invokeInsertThenRollbackWrong() throws RollbackException {
        insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        declarativeTransactionService.insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackByAopContext() throws RollbackException {
        ((DeclarativeTransactionService) AopContext.currentProxy()).insertThenRollback();
    }

}
