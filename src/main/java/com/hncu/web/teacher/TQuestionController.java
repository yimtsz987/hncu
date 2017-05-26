package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Marking;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.TeacherQuestion;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.student.QuestionService;
import com.hncu.service.teacher.TMarkingService;
import com.hncu.service.teacher.TQuestionService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * 教师问题解决控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TQuestionController extends BaseController{

    @Resource
    private TQuestionService tQuestionService;


    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherQuestion get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherQuestion();
        } else {
            return tQuestionService.queryById(id);
        }
    }

    @RequestMapping(value = "/questionList")
    public String markingList(TeacherQuestion teacherQuestion, Model model, PageParam pageParam){
        teacherQuestion.setTeacherId(UserUtils.getCurrentUser().getId());
        PageInfo<TeacherQuestion> teacherQuestionPageInfo = tQuestionService.queryStudentInfoListWithPage(teacherQuestion, pageParam);
        model.addAttribute("teacherQuestionPageInfo", teacherQuestionPageInfo);
        return "teacher/questionList";
    }

}
