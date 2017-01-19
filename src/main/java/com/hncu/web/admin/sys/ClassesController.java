package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Classes;
import com.hncu.entity.Department;
import com.hncu.entity.Major;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.MajorService;
import com.hncu.utils.StringUtils;
import org.springframework.dao.DuplicateKeyException;
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
 * 班级菜单信息控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class ClassesController extends BaseController {

    @Resource
    private ClassesService classesService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private MajorService majorService;

    @ModelAttribute
    public Classes get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Classes();
        } else {
            return classesService.queryById(id);
        }
    }

    @RequestMapping(value = "/classesList")
    public String queryClassesList(Classes classes, Model model, PageParam pageParam){
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);
        List<Major> majorList = majorService.queryList(new Major());
        model.addAttribute("majorList", majorList);

        PageInfo<Classes> classesPageInfo = classesService.queryListWithPage(classes, pageParam);
        model.addAttribute("classesPageInfo", classesPageInfo);
        return "admin/sys/classesList";
    }

    @RequestMapping(value = "/classesEdit")
    public String classesEdit(Classes classes, Model model){
        model.addAttribute("classes", classes);
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);
        List<Major> majorList = majorService.queryList(new Major());
        model.addAttribute("majorList", majorList);
        return "admin/sys/classesEdit";
    }

    @RequestMapping(value = "/saveClasses")
    public String saveClasses(@Valid Classes classes, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return classesEdit(classes, model);
        }
        Msg msg;
        if (!classesService.checkOnly(classes)){
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+classes.getClassId()+"】班级菜单信息已存在！！");
        }else {
            try {
                classesService.save(classes);
                msg = new Msg(Msg.MSG_TYPE_OK, "保存班级菜单信息成功！！");
            } catch (Exception e){
                logger.error("保存班级菜单信息失败！！",e);
                msg = new Msg(Msg.MSG_TYPE_REMOVE, "保存班级菜单信息失败！！");
            }
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/classesList";
    }

    @RequestMapping(value = "/deleteClasses")
    public String deleteClasses(@RequestParam String id,RedirectAttributes redirectAttributes){
        Msg msg;
        try {
            classesService.delete(new Classes(id));
            msg = new Msg(Msg.MSG_TYPE_OK, "删除班级菜单信息成功！！");
        } catch (Exception e) {
            logger.error("删除班级菜单信息失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "删除班级菜单信息失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/classesList";
    }
}
