package com.budan.springappblog.security.exception;

public class ExpiredTokenAuthenticationException extends ArithmeticException {

    public ExpiredTokenAuthenticationException() {
        super("Authentication token is expired");
    }
}
