package com.hncu.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by yimts on 2017-5-8.
 */
public class CaptchaAuthenticationToken extends UsernamePasswordToken {

    private String kaptcha;

    public CaptchaAuthenticationToken (){}

    public CaptchaAuthenticationToken (String username, String password,
                                       boolean rememberMe, String host, String kaptcha) {
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }

    public void setKaptcha(String kaptcha){
        this.kaptcha= kaptcha;
    }

    public String getKaptcha(){
        return this.kaptcha;
    }
}
