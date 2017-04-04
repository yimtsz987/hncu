package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.admin.sys.AnswerGroupService;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.StudentService;
import com.hncu.service.admin.sys.TeacherService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    @Resource
    private TeacherService teacherService;

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

    @RequestMapping(value = "/settingAnswerLeader")
    public String settingAnswerLeader(TeacherInfo teacherInfo, Model model, PageParam pageParam){
        PageInfo<TeacherInfo> teacherInfoPageInfo = teacherService.queryAnswerNotSelectWithPage(teacherInfo, pageParam);
        model.addAttribute("teacherInfoPageInfo", teacherInfoPageInfo);
        return "admin/sys/settingAnswerLeader";
    }

    @RequestMapping(value = "/updateLeader")
    public String updateLeader(@Valid TeacherInfo teacherInfo, BindingResult br, Model model, RedirectAttributes redirectAttributes, PageParam pageParam){
        if (br.hasErrors()){
            return settingAnswerLeader(teacherInfo, model, pageParam);
        }
        Msg msg;
        try {
            answerGroupService.updateLeader(teacherInfo);
            msg = new Msg(Msg.MSG_TYPE_OK, "组长信息保存成功！！");
        } catch (Exception e){
            logger.error("组长信息保存失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "组长信息保存失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/answerGroupList";
    }

    @RequestMapping(value = "/deleteLeader")
    public String deleteLeader(@RequestParam String tId, @RequestParam String queryClasses, RedirectAttributes redirectAttributes){
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.settId(tId);
        teacherInfo.setQueryClasses(queryClasses);
        Msg msg;
        try {
            answerGroupService.deleteLeader(teacherInfo);
            msg = new Msg(Msg.MSG_TYPE_OK, "组长信息撤除成功！！");
        } catch (Exception e){
            logger.error("组长信息撤除失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "组长信息撤除失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/answerGroupList";
        //todo 撤除组长做弹窗
    }

    @RequestMapping(value = "/teacherAnswerInfoList")
    public String teacherAnswerInfoList(Answer answer, Model model){
        List<TeacherInfo> teacherInfoList = answerGroupService.queryAnswerTeacherList(answer);
        Answer answerInfo = answerGroupService.queryById(answer.getId());
        model.addAttribute("teacherInfoList", teacherInfoList);
        model.addAttribute("answerInfo", answerInfo);
        return "admin/sys/teacherAnswerInfoList";
    }

    @RequestMapping(value = "/settingAnswerTeacher")
    public String settingAnswerTeacher(TeacherInfo teacherInfo, Model model, PageParam pageParam){
        PageInfo<TeacherInfo> teacherInfoPageInfo = teacherService.queryAnswerNotSelectWithPage(teacherInfo, pageParam);
        Answer answer = new Answer();
        answer.setId(teacherInfo.getAnswerId());
        model.addAttribute("teacherInfoPageInfo", teacherInfoPageInfo);
        model.addAttribute("teacherInfo", teacherInfo);
        model.addAttribute("answer", answer);
        return "admin/sys/settingAnswerTeacher";
    }

    @RequestMapping(value = "/updateAnswerTeacher")
    public String updateAnswerTeacher(@Valid Answer answer, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            teacherAnswerInfoList(answer, model);
        }
        Msg msg;
        try {
            answerGroupService.updateAnswerTeacher(answer);
            msg = new Msg(Msg.MSG_TYPE_OK, "组员信息保存成功！！");
        } catch (Exception e){
            logger.error("组员信息保存成功！！",e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "组员信息保存失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/answerGroupList";
    }

    @RequestMapping(value = "/deleteAnswerTeacher")
    public String deleteAnswerTeacher(@RequestParam String teacherId, @RequestParam String answerId, @RequestParam String answerClasses, Model model,RedirectAttributes redirectAttributes){
        Answer answer = new Answer();
        answer.setTeacherId(teacherId);
        answer.setId(answerId);
        answer.setAnswerClasses(answerClasses);
        Msg msg;
        try {
            answerGroupService.deleteAnswerTeacher(answer);
            msg = new Msg(Msg.MSG_TYPE_OK, "【" + answerClasses + "】班组员撤除成功！！");
        } catch (Exception e){
            logger.error("【" + answerClasses + "】班组员撤除失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【" + answerClasses + "】班组员撤除失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/answerGroupList";
    }

    @RequestMapping(value = "/settingAnswerTimeInfo")
    public String settingAnswerTimeInfo(Answer answer, Model model){
        model.addAttribute("answer", answer);
        return "admin/sys/answerTimeInfoEdit";
    }

    @RequestMapping(value = "/updateAnswerTimeInfo")
    public String updateAnswerTimeInfo(@Valid Answer answer, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            settingAnswerTimeInfo(answer, model);
        }
        Msg msg;
        try {
            answer.setAnswerTime(DateUtils.parse(answer.getDateString(), "yyyy-MM-dd HH:mm:ss"));
            answerGroupService.updateAnswerTimeInfo(answer);
            msg = new Msg(Msg.MSG_TYPE_OK, "答辩时间信息保存成功！！");
        } catch (Exception e){
            logger.error("答辩时间信息保存成功！！",e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "答辩时间信息保存失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/answerGroupList";
    }
}
