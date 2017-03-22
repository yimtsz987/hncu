package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.Answer;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.User;
import com.hncu.service.AnswerService;
import com.hncu.service.UserService;
import com.hncu.service.teacher.TOpenTitleService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师端 - 查看学生答辩信息控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TAnswerController extends BaseController {

    @Resource
    private TOpenTitleService tOpenTitleService;

    @Resource
    private AnswerService answerService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/studentAnswerList")
    public String studentAnswerList(TeacherOpenTitle teacherOpenTitle, Model model, PageParam pageParam){
        OpenTitle openTitle = new OpenTitle();
        openTitle.setTeacherId(UserUtils.getCurrentUser().getId());
        if(teacherOpenTitle.getOpenTitle() != null){
            if (StringUtils.isNotBlank(teacherOpenTitle.getOpenTitle().getReportFlag())){
                openTitle.setReportFlag(teacherOpenTitle.getOpenTitle().getReportFlag());
            }
        }
        teacherOpenTitle.setOpenTitle(openTitle);
        PageInfo<TeacherOpenTitle> teacherAnswerPageInfo = tOpenTitleService.queryListWithPage(teacherOpenTitle, pageParam);
        model.addAttribute("teacherAnswerPageInfo", teacherAnswerPageInfo);
        return "teacher/studentAnswerList";
    }

    @RequestMapping(value = "/studentAnswerInfoList")
    public String studentAnswerInfoList(@RequestParam String answerId, Model model){
        Answer answer = answerService.queryById(answerId);
        User leader = userService.queryById(answer.getLeaderId());
        String[] teacherId = StringUtils.split(answer.getTeacherIds(),",");
        List<User> teacherList = Lists.newArrayList();
        User user = null;
        for (int i = 0; i < teacherId.length; i++) {
            user = userService.queryById(teacherId[i]);
            teacherList.add(user);
        }
        model.addAttribute("leader", leader);
        model.addAttribute("teacherList", teacherList);
        return "teacher/studentAnswerInfoList";
    }

    @RequestMapping(value = "/answerTeacherInfo")
    public String answerTeacherInfo(@RequestParam String id, Model model){
        User teacher = userService.queryById(id);
        model.addAttribute("teacher", teacher);
        return "student/answerTeacherInfo";
    }

    @RequestMapping(value = "/teacherAnswerInfoList")
    public String teacherAnswerInfoList(Model model){
        Answer answer = answerService.queryById(UserUtils.getCurrentUser().getTeacher().getAnswerId());
        User leader = userService.queryById(answer.getLeaderId());
        String[] teacherId = StringUtils.split(answer.getTeacherIds(),",");
        List<User> teacherList = Lists.newArrayList();
        User user = null;
        for (int i = 0; i < teacherId.length; i++) {
            user = userService.queryById(teacherId[i]);
            teacherList.add(user);
        }
        model.addAttribute("leader", leader);
        model.addAttribute("teacherList", teacherList);
        return "teacher/teacherAnswerInfoList";
    }

}
