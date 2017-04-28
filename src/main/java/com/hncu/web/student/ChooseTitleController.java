package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.Title;
import com.hncu.service.student.ChooseTeacherService;
import com.hncu.service.student.ChooseTitleService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 选择课题控制层
 */
@Controller
@RequestMapping(value = "/student")
public class ChooseTitleController extends BaseController {

    @Resource
    private ChooseTitleService chooseTitleService;

    @Resource
    private ChooseTeacherService chooseTeacherService;

    @ModelAttribute
    public Title get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Title();
        } else {
            return chooseTitleService.queryById(id);
        }
    }

    @RequestMapping(value = "/chooseTitleList")
    public String chooseTitleList(Title title, Model model, PageParam pageParam){
        if (StringUtils.isBlank(UserUtils.getCurrentUser().getStudent().getTitleId())){
            title.setTeacherId(UserUtils.getCurrentUser().getStudent().getTeacherId());
            PageInfo<Title> titlePageInfo = chooseTitleService.queryListWithPage(title, pageParam);
            model.addAttribute("titlePageInfo", titlePageInfo);
            return "student/chooseTitleList";
        } else {
            return "redirect:/student/chooseTitleInfo";
        }
    }

    @RequestMapping(value = "/titleInfoList")
    public String titleInfoList(Title title, Model model, PageParam pageParam){
        title.setTeacherId(title.getTeacherId());
        PageInfo<Title> titlePageInfo = chooseTitleService.queryListWithPage(title, pageParam);
        model.addAttribute("titlePageInfo", titlePageInfo);
        return "student/titleInfoList";
    }

    @RequestMapping(value = "/chooseTitle")
    public String chooseTitle(@RequestParam String id, RedirectAttributes redirectAttributes){
        Title title = chooseTitleService.queryById(id);
        Msg msg;
        try {
            if (title.getSelectFlag().equals("0") && title.getTeacherId().equals(UserUtils.getCurrentUser().getStudent().getTeacherId())){
                chooseTitleService.chooseTitle(title);
                msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+title.getTitle()+"】课题成功！！");
            } else {
                msg = new Msg(Msg.MSG_TYPE_OK, "【"+title.getTitle()+"】课题已被选择！！");
            }
        } catch (Exception e){
            logger.error("选择老师失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+title.getTitle()+"】课题失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/student/chooseTitleInfo";
    }

    @RequestMapping(value = "/chooseTitleInfo")
    public String chooseTitleInfo(Model model){
        if (StringUtils.isNotBlank(UserUtils.getCurrentUser().getStudent().getTitleId())){
            Title title = chooseTitleService.queryById(UserUtils.getCurrentUser().getStudent().getTitleId());
            model.addAttribute("title",title);
            return "student/titleInfo";
        } else {
            return "redirect:/student/chooseTitleList";
        }
    }

    @RequestMapping(value = "/chooseTitleAndTeacher")
    public String chooseTitleAndTeacher(@RequestParam String id, @RequestParam String teacherId,  RedirectAttributes redirectAttributes){
        Title title = chooseTitleService.queryById(id);
        TeacherInfo teacherInfo = chooseTeacherService.queryTeacherInfoById(new TeacherInfo(teacherId));
        try {
            if (title.getSelectFlag().equals("0") && title.getTeacherId().equals(teacherId)
                    && StringUtils.isEmpty(UserUtils.getCurrentUser().getStudent().getTeacherId())
                    && teacherInfo.getStudentSum() <= Integer.parseInt(SysParamUtil.getParamValue("maxTeacherNum"))){
                chooseTitleService.chooseTitleAndTeacher(title, teacherInfo);
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_OK, "选择【"+title.getTitle()+"】课题和【"+ UserUtils.queryUserById(title.getTeacherId()).getName() +"】老师成功！！"));
                return "redirect:/student/chooseTitleInfo";
            } else {
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_REMOVE, "【"+title.getTitle()+"】课题已被选择或者教师学生已满！！"));
                return "redirect:/student/chooseTeacherList";
            }
        } catch (Exception e){
            logger.error("选择课题和老师失败！！", e);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "选择【"+title.getTitle()+"】课题和【"+ UserUtils.queryUserById(title.getTeacherId()).getName() +"】老师失败！！"));
            return "redirect:/student/chooseTeacherList";
        }
    }
}
