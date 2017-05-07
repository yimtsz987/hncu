package com.hncu.web;

import com.hncu.common.BaseController;
import com.hncu.entity.User;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/queryPassword",params = "oldPassword")
    public Map<String, String> queryPassword(HttpServletRequest request, HttpServletResponse response){
        String oldPassword = request.getParameter("param");
        User user = UserUtils.getCurrentUser();
        Map<String, String> map = new HashMap<String, String>();
        /*if (userService.queryPassword(user,oldPassword)){
            map.put("info", "原密码正确");
            map.put("status", "y");
        } else {
            map.put("info", "原密码错误");
            map.put("status", "n");
        }*/
        return map;
    }
}
