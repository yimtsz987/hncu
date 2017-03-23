package com.hncu.web.admin.sys;

import com.hncu.common.BaseController;
import com.hncu.entity.Role;
import com.hncu.entity.Upload;
import com.hncu.service.RoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 上传资料控制层
 */
@Controller
@RequestMapping(value = {"/admin","/secretary"})
public class UploadDateController extends BaseController {

    @Resource
    private RoleService roleService;

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/uploadDate")
    public String uploadDate(Upload upload, Model model){

        model.addAttribute("upload", upload);
        model.addAttribute("roleList", roleService.queryList(new Role()));
        return "admin/sys/upload";
    }
}
