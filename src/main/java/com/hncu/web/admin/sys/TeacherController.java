package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Department;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.TeacherService;
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
 * 教师管理控制层
 */
@Controller
@RequestMapping(value = {"/admin","/secretary"})
public class TeacherController extends BaseController{

    @Resource
    private TeacherService teacherService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private UserService userService;

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @ModelAttribute
    public TeacherInfo get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherInfo();
        } else {
            return teacherService.queryById(id);
        }
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/teacherList")
    public String queryTeacherList(TeacherInfo teacherInfo, Model model, PageParam pageParam){
        PageInfo<TeacherInfo> teacherInfoPageInfo = teacherService.queryListWithPage(teacherInfo, pageParam);
        model.addAttribute("teacherInfoPageInfo", teacherInfoPageInfo);
        return "admin/sys/teacherList";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/teacherEdit")
    public String teacherEdit(TeacherInfo teacherInfo, Model model){
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);

        model.addAttribute("teacherInfo", teacherInfo);
        return "admin/sys/teacherEdit";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
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

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
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

    @RequestMapping(value = "/updateSelectFlag")
    public String updateSelectFlag(@RequestParam String id, @RequestParam String selectFlag, @RequestParam String name,RedirectAttributes redirectAttributes){
        TeacherInfo teacherInfo = new TeacherInfo(id);
        teacherInfo.setSelectFlag(selectFlag);
        Msg msg;
        try {
            teacherService.updateSelectFlag(teacherInfo);
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+ name +"】教师状态修改成功！！");
        } catch (Exception e){
            logger.error("【"+ name +"】教师状态修改失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+ name +"】教师状态修改失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/teacherList";
    }

    @RequestMapping(value = "/resetTeacherPassword")
    public String resetTeacherPassword(@RequestParam String id, RedirectAttributes redirectAttributes){
        User user = userService.queryById(id);
        Msg msg;
        try {
            userService.resetPassword(user);
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+ user.getName() +"】教师密码重置成功！！");
        } catch (Exception e){
            logger.error("【"+ user.getName() +"】教师密码重置失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+ user.getName() +"】教师密码重置失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/teacherList";
    }
}
