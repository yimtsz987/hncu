package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Department;
import com.hncu.entity.Major;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.MajorService;
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
 * 专业菜单信息控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class MajorController extends BaseController {

    @Resource
    private MajorService majorService;

    @Resource
    private DepartmentService departmentService;

    @ModelAttribute
    public Major get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Major();
        } else {
            return majorService.queryById(id);
        }
    }

    @RequestMapping(value = "/majorList")
    public String queryMajorList(Major major, Model model, PageParam pageParam){
        List<String> nameList = majorService.queryMajorNameList();
        model.addAttribute("nameList", nameList);
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);
        PageInfo<Major> majorPageInfo = majorService.queryListWithPage(major, pageParam);
        model.addAttribute("majorPageInfo", majorPageInfo);
        return "admin/sys/majorList";
    }

    @RequestMapping(value = "/majorEdit")
    public String majorEdit(Major major,Model model){
        model.addAttribute("major", major);
        List<Department> departmentList = departmentService.queryList(new Department());
        model.addAttribute("departmentList", departmentList);
        return "admin/sys/majorEdit";
    }

    @RequestMapping(value = "/saveMajor")
    public String saveMajor(@Valid Major major, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return majorEdit(major, model);
        }
        Msg msg;
        if (!majorService.checkOnly(major)){
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "【"+major.getName()+"】信息已存在！！");
        } else {
            try {
                majorService.save(major);
                msg = new Msg(Msg.MSG_TYPE_OK, "保存专业信息成功！！");
            } catch (Exception e){
                logger.error("保存专业信息失败！！");
                msg = new Msg(Msg.MSG_TYPE_REMOVE, "保存专业信息失败！！");
            }
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/majorList";
    }

    @RequestMapping(value = "/deleteMajor")
    public String deleteMajor(@RequestParam String id,RedirectAttributes redirectAttributes){
        Major major = new Major(id);
        Msg msg;
        try {
            majorService.delete(major);
            msg = new Msg(Msg.MSG_TYPE_OK, "删除专业信息成功！！");
        } catch (Exception e) {
            logger.error("删除专业信息失败！！", e);
            msg = new Msg(Msg.MSG_TYPE_REMOVE, "删除专业信息失败！！");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/majorList";
    }
}
