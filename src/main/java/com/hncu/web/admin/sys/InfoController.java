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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String adminInfoPage(User user, Model model){
        model.addAttribute("user", user);
        return "admin/sys/info";
    }

    @RequestMapping(value = "/adminInfoEdit")
    public String adminInfoEdit(User user,Model model){
        model.addAttribute("user", user);
        return "admin/sys/infoEdit";
    }

    @RequestMapping(value = "/saveAdminInfo")
    public String saveAdminInfo(@Valid User user, Model model, BindingResult br, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return adminInfoPage(user, model);
        }
        Msg msg;
        try {
            adminInfoService.updateAdminInfo(user);
            msg = new Msg(Msg.MSG_TYPE_OK, "个人信息成功！！");
        } catch (Exception e){
            logger.error("个人信息失败！！",e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "个人信息失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/info";
    }
}
