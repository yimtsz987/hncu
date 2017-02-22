package com.hncu.web.admin.sys;

import com.hncu.utils.DateUtils;
import com.hncu.utils.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统管理员首页行控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class HomeController {

    @RequestMapping(value = "home")
    public String home(HttpServletRequest request, Model model){
        String loginIp = RequestUtil.getRemoteAddr(request);
        model.addAttribute("loginIp", loginIp);
        model.addAttribute("nowDate", DateUtils.getDateTime());
        return "admin/sys/home";
    }
}
