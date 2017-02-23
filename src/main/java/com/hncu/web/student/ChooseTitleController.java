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

    private static final long serialVersionUID = -8828412962426421254L;
    @Resource
    private ChooseTitleService chooseTitleService;

    @ModelAttribute
    public Title get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Title();
        } else {
            return chooseTitleService.queryById(id);
        }
    }

    @RequestMapping(value = "/chooseTitleList")
    public String chooseTeacherList(Title title, Model model, PageParam pageParam){
        if (StringUtils.isBlank(UserUtils.getCurrentUser().getStudent().getTitleId())){
            PageInfo<Title> titlePageInfo = chooseTitleService.queryListWithPage(title, pageParam);
            model.addAttribute("titlePageInfo", titlePageInfo);
            return "student/chooseTitleList";
        } else {
            return "redirect:/student/chooseTitleInfo";
        }
    }

    @RequestMapping(value = "/chooseTitle")
    public String chooseTitle(@RequestParam String id, Model model, RedirectAttributes redirectAttributes){
        Title title = chooseTitleService.queryById(id);
        Msg msg;
        try {
            chooseTitleService.chooseTitle(title,UserUtils.getCurrentUser());
            msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+title.getTitle()+"】老师成功！！");
        } catch (Exception e){
            logger.error("选择老师失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_OK, "选择【"+title.getTitle()+"】老师失败！！");
        }
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
}
