package com.hncu.web.admin.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理员信息控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class InfoController {

    @RequestMapping(value = "/info")
    public String info(){
        return "admin/sys/info";
    }
}
