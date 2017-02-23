package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.BaseEntity;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import com.hncu.service.admin.sys.TeacherService;
import com.hncu.service.student.ChooseTeacherService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 选择教师控制层
 */
@Controller
@RequestMapping(value = "/student")
public class ChooseTeacherController extends BaseController {

    private static final long serialVersionUID = -8828412962426421254L;
    @Resource
    private ChooseTeacherService chooseTeacherService;

    @ModelAttribute
    public TeacherInfo get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherInfo();
        } else {
            return chooseTeacherService.queryById(id);
        }
    }

    @RequestMapping(value = "/chooseTeacherList")
    public String chooseTeacherList(TeacherInfo teacherInfo, Model model, PageParam pageParam){
        PageInfo<TeacherInfo> teacherInfoPageInfo = chooseTeacherService.queryListWithPage(teacherInfo, pageParam);

        model.addAttribute("teacherInfoPageInfo", teacherInfoPageInfo);
        return "student/chooseTeacherList";
    }

    @RequestMapping(value = "/chooseTeacher")
    public String chooseTeacher(@RequestParam String id, Model model, RedirectAttributes redirectAttributes){
        TeacherInfo teacherInfo = chooseTeacherService.queryById(id);
        Msg msg;
        try {
            chooseTeacherService.chooseTeacher(teacherInfo,UserUtils.getCurrentUser());
            msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+teacherInfo.getName()+"】老师成功！！");
        } catch (Exception e){
            logger.error("选择老师失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+teacherInfo.getName()+"】老师失败！！");
        }
        return "redirect:/student/chooseTeacherInfo";
    }

    @RequestMapping(value = "/chooseTeacherInfo")
    public String chooseTeacherInfo(Model model){
        TeacherInfo teacherInfo = chooseTeacherService.queryById(UserUtils.getCurrentUser().getStudent().getTeacher().gettId());
        model.addAttribute("teacherInfo",teacherInfo);
        return "student/teacherInfo";
    }
}
