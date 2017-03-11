package com.hncu.web.teacher;

import com.hncu.common.BaseController;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.teacher.TStudentService;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师学生信息控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherStudentController extends BaseController{

    @Resource
    private TStudentService tStudentService;

    @Resource
    private UserService userService;


    @RequestMapping(value = "/studentList")
    public String studentList(Model model){
        List<User> studentList = tStudentService.queryStudentList(UserUtils.getCurrentUser().getId(), SysParamUtil.getParamValue("year"));
        model.addAttribute("studentList", studentList);
        return "teacher/studentList";
    }

    @RequestMapping(value = "/studentInfo")
    public String studentInfo(@RequestParam String id, Model model){
        User studentInfo = userService.queryById(id);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/studentInfo";
    }
}
