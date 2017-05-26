package com.hncu.web;

import com.hncu.entity.Link;
import com.hncu.entity.User;
import com.hncu.security.FormAuthFilter;
import com.hncu.security.Principal;
import com.hncu.service.LinkService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制层
 */
@Controller
public class LoginController {

    @Resource
    private LinkService linkService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("link", linkService.queryList(new Link()));
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

        model.addAttribute("link", linkService.queryList(new Link()));
        return "login";
    }

    @RequestMapping(value = "/forward")
    public String forward(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            return "redirect:/admin/index";
        } else if (subject.hasRole("teacher") && subject.hasRole("secretary")){
            return "redirect:/tsRoleSelect";
        } else if(subject.hasRole("teacher") && subject.hasRole("director")){
            return "redirect:/tdRoleSelect";
        } else if (subject.hasRole("teacher")){
            return "redirect:/teacher/index";
        } else if (subject.hasRole("student")){
            return "redirect:/student/index";
        } else {
            return null;
        }
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "/admin/index")
    public String adminIndex(){
        return "admin/sys/index";
    }

    @RequiresPermissions("secretary")
    @RequestMapping(value = "/secretary/index")
    public String secretaryIndex(){
        return "admin/secretary/index";
    }

    @RequiresPermissions("secretary")
    @RequestMapping(value = "/tsRoleSelect")
    public String tsRoleSelect(){
        return "admin/tsRoleSelect";
    }

    @RequiresPermissions("director")
    @RequestMapping(value = "/tdRoleSelect")
    public String tdRoleSelect(){
        return "admin/tdRoleSelect";
    }

    @RequiresPermissions("teacher")
    @RequestMapping(value = "/teacher/index")
    public String teacherIndex(){
        return "teacher/index";
    }

    @RequiresPermissions("teacher")
    @RequestMapping(value = "/trRoleSelect")
    public String trRoleSelect(){
        return "teacher/trRoleSelect";
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
