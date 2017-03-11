package com.hncu.web.teacher;

import com.hncu.common.BaseController;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.service.teacher.TStudentService;
import com.hncu.service.teacher.TUnderstandingService;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师端 - 审题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TUnderstandingController extends BaseController{

    @Resource
    private TUnderstandingService tUnderstandingService;

    @Resource
    private TStudentService tStudentService;

    @RequestMapping(value = "/understandingList")
    public String understandingList(Understanding understanding, Model model){
        List<TeacherUnderstanding> teacherUnderstandingList = tUnderstandingService.queryUnderstantList();
        model.addAttribute("teacherUnderstandingList", teacherUnderstandingList);
        return "teacher/understandingList";
    }
}
