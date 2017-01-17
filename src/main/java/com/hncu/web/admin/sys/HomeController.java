package com.hncu.web.admin.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理员首页行控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class HomeController {

    @RequestMapping(value = "home")
    public String home(){
        return "admin/sys/home";
    }
}
