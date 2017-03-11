package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Title;
import com.hncu.service.teacher.TitleService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 教师端 - 课题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TitleController extends BaseController {

    @Resource
    private TitleService titleService;

    @ModelAttribute
    public Title get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Title();
        } else {
            return titleService.queryById(id);
        }
    }

    @RequestMapping(value = "/titleList")
    public String titleList(Title title, Model model, PageParam pageParam){
      title.setTeacherId(UserUtils.getCurrentUser().getId());
      PageInfo<Title> titlePageInfo = titleService.queryListWithPage(title, pageParam);
      model.addAttribute("titlePageInfo", titlePageInfo);
      return "teacher/titleList";
    }

    @RequestMapping(value = "/titleEdit")
    public String titleEdit(Title title, Model model){
        return "teacher/titleEdit";
    }

    @RequestMapping(value = "/saveTitle")
    public String saveTitle(@Valid Title title, BindingResult br, Model model, RedirectAttributes redirectAttributes,PageParam pageParam){
        if (br.hasErrors()){
            return titleList(title,model,pageParam);
        }
        Msg msg;
        try {
            titleService.saveTitle(title);
            msg = new Msg(Msg.MSG_TYPE_OK, "保存【"+title.getTitle()+"】课题成功！！");
        } catch (Exception e){
            logger.error("保存【"+title.getTitle()+"】课题失败！！",e);
            msg = new Msg(Msg.MSG_TYPE_OK, "保存【"+title.getTitle()+"】课题失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/teacher/titleList";
    }

    @RequestMapping(value = "/deleteTitle")
    public String deleteTitle(@RequestParam String id, @RequestParam String title, Model model, RedirectAttributes redirectAttributes){
        Msg msg;
        Title title1 = titleService.queryById(id);
        if (title1 != null){
            if (title.equals(MD5Util.string2MD5(title1.getTitle()))){
                try {
                    titleService.deleteTitle(id);
                    msg = new Msg(Msg.MSG_TYPE_OK, "删除课题【"+title1.getTitle()+"】成功！");
                } catch (Exception e){
                    logger.error("删除课题【"+title+"】失败", e);
                    msg = new Msg(Msg.MSG_TYPE_OK, "删除课题【"+title+"】失败");
                }
            } else {
                msg = new Msg(Msg.MSG_TYPE_REMOVE, "删除课题【"+title1.getTitle()+"】失败！");
            }
        } else {
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "查询课题失败！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/teacher/titleList";
    }
}
