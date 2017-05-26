package com.hncu.security;

import com.google.code.kaptcha.Constants;
import com.hncu.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 表单验证的过滤器
 */
@Component
public class FormAuthFilter extends FormAuthenticationFilter {

    public static final String MESSAGE_PARAM = "message";

    private String messageParam = MESSAGE_PARAM;

    public String getMessageParam() {
        return messageParam;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e,
                                     ServletRequest request,
                                     ServletResponse response) {
        String message;
        if (e instanceof IncorrectCredentialsException
                || e instanceof UnknownAccountException) {
            message = "用户名或密码不正确";
        } else if (e instanceof IncorrectCaptchaException){
            message = "验证码错误";
        } else {
            message = "服务器内部错误，请稍后再试";
        }
        request.setAttribute(messageParam, message);
        return true;
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String successUrl = getSuccessUrl();
        WebUtils.issueRedirect(request, response, successUrl, null, true);
    }

    private String captchaParam = "kaptcha";

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request,
                                              ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaAuthenticationToken(username, password, rememberMe,
                host, captcha);
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaAuthenticationToken token) {
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (StringUtils.isEmpty(token.getKaptcha()) || !token.getKaptcha().equalsIgnoreCase(captcha)) {
            /* 定义IncorrectCaptchaException，shiro显示Exception class name作为error信息 */
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }

    // 认证
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaAuthenticationToken token = (CaptchaAuthenticationToken) createToken(request, response);

        try {
            doCaptchaValidate((HttpServletRequest) request, token);

            Subject subject = getSubject(request, response);
            subject.login(token);

            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }
}
