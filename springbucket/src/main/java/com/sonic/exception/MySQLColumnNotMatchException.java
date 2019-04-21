package com.sonic.exception;

import org.springframework.dao.DataAccessException;

/**
 * MySQLColumnNotMatchException
 *
 * @author Sonic
 * @since 2019/4/21
 */
public class MySQLColumnNotMatchException extends DataAccessException {
    public MySQLColumnNotMatchException(String msg) {
        super(msg);
    }

    public MySQLColumnNotMatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
