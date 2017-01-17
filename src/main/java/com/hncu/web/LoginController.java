package com.hncu.web;

import com.hncu.entity.User;
import com.hncu.security.FormAuthFilter;
import com.hncu.security.Principal;
import com.hncu.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制层
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin (Model model) {
        model.addAttribute("user", new User());
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model){
        Principal principal = UserUtils.getPrincipal();
        if (principal != null) {
            return forward();
        }
        String username = WebUtils.getCleanParam(request, FormAuthFilter.DEFAULT_USERNAME_PARAM);
        boolean rememberMe = WebUtils.isTrue(request, FormAuthFilter.DEFAULT_REMEMBER_ME_PARAM);
        String exception = (String) request.getAttribute(FormAuthFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String) request.getAttribute(FormAuthFilter.MESSAGE_PARAM);

        model.addAttribute(FormAuthFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
        model.addAttribute(FormAuthFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
        model.addAttribute(FormAuthFilter.MESSAGE_PARAM, message);

        return "login";
    }

    @RequestMapping(value = "/forward")
    public String forward(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            return "redirect:/admin/index";
        } else if (subject.hasRole("teacher") && subject.hasRole("t_admin")){
            return "redirect:/admin/index_teacher";
        } else if (subject.hasRole("teacher")){
            return "redirect:/teacher/index";
        } else if (subject.hasRole("student")){
            return "redirect:/student/index";
        } else {
            return "redirect:/student/index";
        }
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "/admin/index")
    public String adminIndex(){
        return "admin/sys/index";
    }

    @RequiresPermissions("secretary")
    @RequestMapping(value = "/admin/index_teacher")
    public String tAdminIndex(){
        return "admin/index_teacher";
    }

    @RequiresPermissions("teacher")
    @RequestMapping(value = "/teacher/index")
    public String teacherIndex(){
        return "teacher/index";
    }

    @RequiresPermissions("student")
    @RequestMapping(value = "/student/index")
    public String studentIndex(){
        return "student/index";
    }

    @RequestMapping(value = "/403")
    public String error403(){
        return "403";
    }
}
