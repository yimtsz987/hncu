package com.hncu.web.student;

import com.google.common.collect.Lists;
import com.hncu.common.BaseController;
import com.hncu.entity.Answer;
import com.hncu.entity.User;
import com.hncu.service.AnswerService;
import com.hncu.service.UserService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答辩学生端控制层
 */
@Controller
@RequestMapping(value = "/student")
public class StudentAnswerController extends BaseController{

    @Resource
    private AnswerService answerService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/answerList")
    public String answerList(Model model){
        Answer answer = answerService.queryByClasses(UserUtils.getCurrentUser().getStudent().getClasses());
        Boolean answerFlag = false;
        if (StringUtils.isNotEmpty(answer.getLeaderId())){
            User leader = userService.queryById(answer.getLeaderId());
            if (StringUtils.isNotEmpty(answer.getTeacherIds())){
                String[] teacherId = StringUtils.split(answer.getTeacherIds(),",");
                List<User> teacherList = Lists.newArrayList();
                User user = null;
                for (int i = 0; i < teacherId.length; i++) {
                    user = userService.queryById(teacherId[i]);
                    teacherList.add(user);
                }
                answerFlag = true;
                model.addAttribute("leader", leader);
                model.addAttribute("teacherList", teacherList);
                model.addAttribute("answerFlag", answerFlag);
            } else {
                model.addAttribute("answer",answer);
                model.addAttribute("answerFlag", answerFlag);
            }
        } else {
            model.addAttribute("answer",answer);
            model.addAttribute("answerFlag", answerFlag);
        }
        return "student/answerList";
    }

    @RequestMapping(value = "/answerTeacherInfo")
    public String answerTeacherInfo(@RequestParam String id, Model model){
        User teacher = userService.queryById(id);
        model.addAttribute("teacher", teacher);
        return "student/answerTeacherInfo";
    }
}
