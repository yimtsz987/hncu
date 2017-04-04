package com.hncu.web;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Notice;
import com.hncu.entity.Role;
import com.hncu.service.NoticeService;
import com.hncu.service.RoleService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 通知公告控制层
 */
@Controller
@RequestMapping(value = "notice")
public class NoticeController extends BaseController{

    @Resource
    private NoticeService noticeService;

    @Resource
    private RoleService roleService;

    @ModelAttribute
    public Notice get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Notice();
        } else {
            return noticeService.queryById(id);
        }
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/noticeList")
    public String noticeAdminList(Notice notice, Model model, PageParam pageParam){
        PageInfo<Notice> noticePageInfo = noticeService.queryListWithPage(notice, pageParam);
        model.addAttribute("noticePageInfo", noticePageInfo);
        return "admin/sys/noticeList";
    }

    @RequestMapping(value = "/noticeTeacherList")
    public String noticeTeacherList(Notice notice, Model model, PageParam pageParam){
        PageInfo<Notice> noticePageInfo = noticeService.queryTeacherNoticeListWithPage(notice, pageParam);
        model.addAttribute("noticePageInfo", noticePageInfo);
        return "teacher/noticeList";
    }

    @RequestMapping(value = "/noticeStudentList")
    public String noticeStudentList(Notice notice, Model model, PageParam pageParam){
        PageInfo<Notice> noticePageInfo = noticeService.queryStudentNoticeListWithPage(notice, pageParam);
        model.addAttribute("noticePageInfo", noticePageInfo);
        return "teacher/noticeList";
    }

    @RequestMapping(value = "/noticeContent")
    public String noticeContent(Notice notice, Model model){
        model.addAttribute("notice", notice);
        return "admin/sys/noticeContent";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/noticeEdit")
    public String noticeEdit(Notice notice, Model model){
        model.addAttribute("notice", notice);
        model.addAttribute("roleList", roleService.queryList(new Role()));
        return "admin/sys/noticeEdit";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/saveNotice")
    public String saveNotice(@Valid Notice notice, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return noticeEdit(notice, model);
        }
        try {
            noticeService.saveNoticeByRole(notice);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "通知公告发送成功！！"));
        }catch (Exception e){
            logger.error("通知公告发送失败！！", e);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "通知公告发送失败！！"));
        }
        return "redirect:/notice/noticeList";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/deleteNotice")
    public String deleteNotice(@RequestParam String id, @RequestParam String checkId, RedirectAttributes redirectAttributes){
        if (MD5Util.string2MD5(id).equals(checkId)){
            Notice notice = new Notice(id);
            try {
                noticeService.deleteNotice(notice);
                redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "通知公告删除成功！！"));
            }catch (Exception e){
                logger.error("通知公告删除失败！！", e);
                redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "通知公告删除失败！！"));
            }
        } else {
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "对不起，操作有误！！"));
        }
        return "redirect:/notice/noticeList";
    }
}
