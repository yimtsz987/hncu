package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Department;
import com.hncu.service.admin.sys.DepartmentService;
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
 * 院系信息控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class DepartmentController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    @ModelAttribute
    public Department get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Department();
        } else {
            return departmentService.queryById(id);
        }
    }

    @RequestMapping(value = "/departmentList")
    public String queryDepartmentList(Department department, Model model, PageParam pageParam){
        List<String> nameList = departmentService.queryNameList();
        model.addAttribute("nameList", nameList);
        List<String> nodeList = departmentService.queryNodeList();
        model.addAttribute("nodeList", nodeList);

        PageInfo<Department> departmentPageInfo = departmentService.queryListWithPage(department, pageParam);
        model.addAttribute("departmentPageInfo", departmentPageInfo);
        return "admin/sys/departmentList";
    }

    @RequestMapping(value = "/departmentEdit")
    public String departmentEdit(Department department, Model model){
        model.addAttribute("department", department);
        return "admin/sys/departmentEdit";
    }

    @RequestMapping(value = "/saveDepartment")
    public String saveDepartment(@Valid Department department, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return departmentEdit(department, model);
        }
        Msg msg;
        if (!departmentService.checkOnly(department)){
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+department.getName()+"】信息已存在！！");
        } else {
            try {
                departmentService.save(department);
                msg = new Msg(Msg.MSG_TYPE_OK, "保存院系菜单信息成功！！");
            } catch (Exception e){
                logger.error("保存院系菜单信息失败！！");
                msg = new Msg(Msg.MSG_TYPE_REMOVE, "保存院系菜单信息失败！！");
            }
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/departmentList";
    }

    @RequestMapping(value = "/deleteDepartment")
    public String deleteDepartment(@RequestParam String id,RedirectAttributes redirectAttributes){
        Department department = new Department(id);
        Msg msg;
        try {
            departmentService.delete(department);
            msg = new Msg(Msg.MSG_TYPE_OK, "删除院系菜单信息成功！！");
        } catch (Exception e) {
            logger.error("删除字典信息失败!", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "删除院系菜单信息失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/departmentList";
    }
}
