package com.sonic.exception;

/**
 * UserNotExistException
 *
 * @author Sonic
 * @since 2019/5/22
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("User not exist");
    }
}
