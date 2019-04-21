package com.sonic.exception;

/**
 * RollbackException
 *
 * @author Sonic
 * @since 2019/4/21
 */
public class RollbackException extends Exception {
    public RollbackException(String message) {
        super(message);
    }
}