package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Department;
import com.hncu.entity.TeacherInfo;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.TeacherService;
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
 * 教师管理控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class TeacherController extends BaseController{

    @Resource
    private TeacherService teacherService;

    @Resource
    private DepartmentService departmentService;

    @ModelAttribute
    public TeacherInfo get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherInfo();
        } else {
            return teacherService.queryById(id);
        }
    }

    @RequestMapping(value = "/teacherList")
    public String queryTeacherList(TeacherInfo teacherInfo, Model model, PageParam pageParam){
        PageInfo<TeacherInfo> teacherInfoPageInfo = teacherService.queryListWithPage(teacherInfo, pageParam);
        model.addAttribute("teacherInfoPageInfo", teacherInfoPageInfo);
        return "admin/sys/teacherList";
    }

    @RequestMapping(value = "/teacherEdit")
    public String teacherEdit(TeacherInfo teacherInfo, Model model){
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);

        model.addAttribute("teacherInfo", teacherInfo);
        return "admin/sys/teacherEdit";
    }

    @RequestMapping(value = "/saveTeacherInfo")
    public String saveTeacherInfo(@Valid TeacherInfo teacherInfo, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return teacherEdit(teacherInfo, model);
        }
        Msg msg;
        try {
            if (StringUtils.isBlank(teacherInfo.getId())){
                if (!teacherService.checkOnly(teacherInfo)){
                    msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+teacherInfo.getNode()+"】教师信息已存在！！");
                    model.addAttribute("msg", msg);
                    return teacherEdit(teacherInfo, model);
                } else {
                    teacherService.saveTeacherInfo(teacherInfo);
                    msg = new Msg(Msg.MSG_TYPE_OK, "【"+teacherInfo.getNode()+"】教师信息保存成功！！");
                }
            }else {
                teacherService.saveTeacherInfo(teacherInfo);
                msg = new Msg(Msg.MSG_TYPE_OK, "【"+teacherInfo.getNode()+"】教师信息保存成功！！");
            }
        } catch (Exception e){
            logger.error("学生信息保存失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+teacherInfo.getNode()+"】教师信息保存失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/teacherList";
    }

    @RequestMapping(value = "/deleteTeacherInfo")
    public String deleteTeacherInfo(@RequestParam String id, RedirectAttributes redirectAttributes){
        TeacherInfo teacherInfo = new TeacherInfo(id);
        Msg msg;
        try {
            teacherService.delete(teacherInfo);
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+teacherInfo.getNode()+"】教师信息删除成功！！");
        } catch (Exception e){
            logger.error("教师信息删除失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+teacherInfo.getNode()+"】教师信息删除失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/teacherList";
    }
}