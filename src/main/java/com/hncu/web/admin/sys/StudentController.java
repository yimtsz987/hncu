package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.MajorService;
import com.hncu.service.admin.sys.StudentService;
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
 * 学生管理控制层
 */
@Controller
@RequestMapping(value = {"/admin","/secretary"})
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;

    @Resource
    private MajorService majorService;

    @Resource
    private ClassesService classesService;

    @Resource
    private DepartmentService departmentService;

    @ModelAttribute
    public StudentInfo get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new StudentInfo();
        } else {
            return studentService.queryById(id);
        }
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/studentList")
    public String queryStudentList(StudentInfo studentInfo, Model model, PageParam pageParam){
        List<Major> majorList =  majorService.queryList(new Major());
        List<Classes> classesList = classesService.queryList(new Classes());
        model.addAttribute("majorList", majorList);
        model.addAttribute("classesList", classesList);

        PageInfo<StudentInfo> userPageInfo = studentService.queryListWithPage(studentInfo, pageParam);
        model.addAttribute("userPageInfo", userPageInfo);
        return "admin/sys/studentList";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/studentEdit")
    public String studentEdit(StudentInfo studentInfo, Model model){
        List<Major> majorList =  majorService.queryList(new Major());
        List<Classes> classesList = classesService.queryList(new Classes());
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("majorList", majorList);
        model.addAttribute("classesList", classesList);
        model.addAttribute("departmentList", departmentList);

        model.addAttribute("studentInfo", studentInfo);
        return "admin/sys/studentEdit";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/saveStudentInfo")
    public String saveStudentInfo(@Valid StudentInfo studentInfo, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return studentEdit(studentInfo,model);
        }
        Msg msg;
        try {
            if (StringUtils.isBlank(studentInfo.getId())){
                if (!studentService.checkOnly(studentInfo)){
                    msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+studentInfo.getNode()+"】信息已存在！！");
                    model.addAttribute("msg", msg);
                    return studentEdit(studentInfo, model);
                } else {
                    studentService.saveStudentInfo(studentInfo);
                    msg = new Msg(Msg.MSG_TYPE_OK, "【"+studentInfo.getNode()+"】信息保存成功！！");
                }
            }else {
                studentService.saveStudentInfo(studentInfo);
                msg = new Msg(Msg.MSG_TYPE_OK, "【"+studentInfo.getNode()+"】信息保存成功！！");
            }
        } catch (Exception e){
            logger.error("学生信息保存失败！！");
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+studentInfo.getNode()+"】信息保存失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/studentList";
    }

    @RequiresPermissions(value = {"admin","secretary"}, logical = Logical.OR)
    @RequestMapping(value = "/deleteStudentInfo")
    public String deleteStudentInfo(@RequestParam String id, RedirectAttributes redirectAttributes){
        StudentInfo studentInfo = new StudentInfo(id);
        Msg msg;
        try {
            studentService.delete(studentInfo);
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+studentInfo.getNode()+"】信息删除成功！！");
        } catch (Exception e){
            logger.error("学生信息删除失败！！");
            msg = new Msg(Msg.MSG_TYPE_OK, "【"+studentInfo.getNode()+"】信息删除失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/studentList";
    }
}
