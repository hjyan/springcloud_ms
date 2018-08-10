package com.hongdian.sys.auth.exception;

public class JwtTokenInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JwtTokenInvalidException() {
        super("token is invalid or expired !");
    }

}
