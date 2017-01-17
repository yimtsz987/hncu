package com.hncu.security;

import com.hncu.service.UserService;
import com.hncu.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by yimts on 2017-1-15.
 */
public class RememberAuthenticationFilter extends FormAuthenticationFilter {

    @Resource
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);

        if (!subject.isAuthenticated() && subject.isRemembered()){
            Session session = subject.getSession(true);

            if (session.getAttribute("userId") == null){
                String username = subject.getPrincipal().toString();

                UserUtils.putCache("userId", userService.queryUserByUsername(username).getId());
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
