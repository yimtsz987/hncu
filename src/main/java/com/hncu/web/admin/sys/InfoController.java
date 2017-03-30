package com.hncu.web.admin.sys;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.admin.sys.AdminInfoService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 系统管理员信息控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class InfoController extends BaseController{

    @Resource
    private AdminInfoService adminInfoService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
         return adminInfoService.queryById(UserUtils.getCurrentUser().getId());
    }

    @RequestMapping(value = "/info")
    public String updateInfo(@Valid User user, BindingResult br, Model model){
        if (br.hasErrors()){
            return "admin/sys/info";
        }
        Msg msg;
        try {
            adminInfoService.updateAdminInfo(user);
            msg = new Msg(Msg.MSG_TYPE_OK, "保存个人信息成功！！");
        } catch (Exception e){
            logger.error("保存个人信息失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "保存个人信息失败！！");
        }
        model.addAttribute("msg", msg);
        return "admin/sys/info";
    }
}
