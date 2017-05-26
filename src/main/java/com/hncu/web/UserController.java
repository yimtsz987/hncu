package com.hncu.web;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/changePwdEdit")
    public String changePwdEdit(){
        return "changePwd";
    }

    @ResponseBody
    @RequestMapping(value = "/queryPassword",params = "oldPassword")
    public Map<String, String> queryPassword(HttpServletRequest request, HttpServletResponse response){
        String oldPassword = request.getParameter("param");
        User user = UserUtils.getCurrentUser();
        Map<String, String> map = new HashMap<String, String>();
        if (userService.validatePassword(oldPassword,user.getPassword())){
            map.put("info", "原密码正确");
            map.put("status", "y");
        } else {
            map.put("info", "原密码错误");
            map.put("status", "n");
        }
        return map;
    }

    @RequestMapping(value = "/savePassword")
    public String savePassword(@RequestParam String newPassword, Model model){
        User user = UserUtils.getCurrentUser();
        try {
            User newUser = new User(user.getId(), user.getUsername());
            newUser.setPassword(userService.enctypePassword(newPassword));
            userService.updateUserPassword(newUser);
            model.addAttribute("msg", new Msg(Msg.MSG_TYPE_OK, "修改密码成功!"));
        } catch (Exception e) {
            logger.error("修改密码失败!" , e);
            model.addAttribute("msg", new Msg(Msg.MSG_TYPE_REMOVE, "修改密码失败!"));
        }
        return "changePwd";
    }
}
