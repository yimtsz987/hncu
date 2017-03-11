package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.service.teacher.TStudentService;
import com.hncu.service.teacher.TUnderstandingService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute
    public TeacherUnderstanding get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherUnderstanding();
        } else {
            return tUnderstandingService.queryById(id);
        }
    }

    @RequestMapping(value = "/understandingList")
    public String understandingList(TeacherUnderstanding teacherUnderstanding, Model model, PageParam pageParam){
        Understanding understanding = new Understanding();
        understanding.setTeacherId(UserUtils.getCurrentUser().getId());
        teacherUnderstanding.setUnderstanding(understanding);
        PageInfo<TeacherUnderstanding> teacherUnderstandingPageInfo = tUnderstandingService.queryListWithPage(teacherUnderstanding, pageParam);
        model.addAttribute("teacherUnderstandingPageInfo", teacherUnderstandingPageInfo);
        return "teacher/understandingList";
    }
}
