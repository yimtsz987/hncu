package com.hncu.web.student;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.User;
import com.hncu.service.InfoService;
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
 * 个人信息控制层  -  学生端
 */
@Controller
@RequestMapping(value = "/student")
public class InfoStudentController extends BaseController{

    @Resource
    private InfoService infoService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        return infoService.queryById(UserUtils.getCurrentUser().getId());
    }

    @RequestMapping(value = "/info")
    public String studentInfoPage(User user, Model model){
        model.addAttribute("user", user);
        return "student/info";
    }

    @RequestMapping(value = "/studentInfoEdit")
    public String studentInfoEdit(User user,Model model){
        model.addAttribute("user", user);
        return "student/infoEdit";
    }

    @RequestMapping(value = "/saveStudentInfo")
    public String saveStudentInfo(@Valid User user, Model model, BindingResult br, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return studentInfoEdit(user, model);
        }
        Msg msg;
        try {
            infoService.updateTeacherInfo(user);
            msg = new Msg(Msg.MSG_TYPE_OK, "个人信息修改成功！！");
        } catch (Exception e){
            logger.error("个人信息修改失败！！",e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "个人信息修改失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/student/info";
    }
}
