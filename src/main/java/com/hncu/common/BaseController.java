package com.hncu.common;

import org.apache.ibatis.binding.BindingException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 控制器父类
 */
public class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /*@ExceptionHandler({BindingException.class,
            ConstraintViolationException.class,
            ValidationException.class})
    public String bingException () {
        return "error/400";
    }

    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class})
    public String authException () {
        return "error/403";
    }
    */
}
