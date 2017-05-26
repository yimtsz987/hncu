package com.hncu.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by yimts on 2017-5-8.
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = -7631373390349090036L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
}
