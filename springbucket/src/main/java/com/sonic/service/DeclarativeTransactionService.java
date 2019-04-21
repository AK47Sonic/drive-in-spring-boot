package com.sonic.service;

import com.sonic.exception.RollbackException;

/**
 * DeclarativeTransactionService
 *
 * @author Sonic
 * @since 2019/4/21
 */
public interface DeclarativeTransactionService {
    void insertRecord();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollbackWrong() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;

    void invokeInsertThenRollbackByAopContext() throws RollbackException;
}
