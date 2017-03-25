package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.Answer;
import com.hncu.entity.Classes;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.User;
import com.hncu.service.admin.sys.AnswerGroupService;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.StudentService;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 答辩分组控制层
 */
@Controller
@RequestMapping("/admin")
public class AnswerGroupController extends BaseController{

    @Resource
    private AnswerGroupService answerGroupService;

    @Resource
    private StudentService studentService;

    @Resource
    private ClassesService classesService;

    @ModelAttribute
    public Answer get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Answer();
        } else {
            return answerGroupService.queryById(id);
        }
    }

    @RequestMapping(value = "/answerGroupList")
    public String answerGroupList(Answer answer, Model model, PageParam pageParam){

        PageInfo<Answer> answerPageInfo = answerGroupService.queryListWithPage(answer, pageParam);
        model.addAttribute("answerPageInfo", answerPageInfo);
        return "admin/sys/answerGroupList";
    }

    @RequestMapping(value = "/classesInfoList")
    public String queryStudentByClasses(StudentInfo studentInfo, Model model, PageParam pageParam){
        PageInfo<StudentInfo> studentInfoPageInfo = studentService.queryStudentListByClassesWithPage(studentInfo, pageParam);
        Classes classesInfo = classesService.queryByClasses(studentInfo.getClasses());
        model.addAttribute("studentInfoPageInfo",studentInfoPageInfo);
        model.addAttribute("classesInfo", classesInfo);
        return "admin/sys/classesInfoList";
    }
}
