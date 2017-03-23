package com.hncu.web.admin.secretary;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.TeacherAndStudent;
import com.hncu.service.admin.secretary.ReviewGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 评阅分组控制层
 */
@Controller
@RequestMapping(value = "/secretary")
public class ReviewGroupController extends BaseController{

    @Resource
    private ReviewGroupService reviewGroupService;

    @RequestMapping(value = "/reviewGroupList")
    public String reviewGroupList(TeacherAndStudent teacherAndStudent, Model model, PageParam pageParam){
        PageInfo<TeacherAndStudent> teacherAndStudentPageInfo = reviewGroupService.queryListWithPage(teacherAndStudent, pageParam);
        model.addAttribute("teacherAndStudentPageInfo", teacherAndStudentPageInfo);
        return "admin/secretary/reviewGroupList";
    }

    @RequestMapping(value = "/randomGroup")
    public String randomGroup(RedirectAttributes redirectAttributes){
        Msg msg;
        Boolean randomGroupFlag = false;
        randomGroupFlag = reviewGroupService.randomGroup();
        if (randomGroupFlag){
            redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_OK, "随机分组成功！！"));
        } else {
            redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_REMOVE, "随机分组失败！！"));
        }
        return "redirect:/secretary/reviewGroupList";
    }
}
