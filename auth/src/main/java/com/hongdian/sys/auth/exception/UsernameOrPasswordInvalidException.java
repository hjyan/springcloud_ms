package com.hongdian.sys.auth.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameOrPasswordInvalidException() {
        super("userId or password doesn't exist !");
    }

}
