package com.budan.springappblog.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenAuthenticationException extends AuthenticationException {

    public InvalidTokenAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidTokenAuthenticationException(String msg) {
        super(msg);
    }
}
