package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.SysParam;
import com.hncu.service.admin.sys.SysParamService;
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
 * 系统参数控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class SysParamController extends BaseController {

    @Resource
    private SysParamService sysParamService;

    @ModelAttribute
    public SysParam get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new SysParam();
        } else {
            return sysParamService.queryById(id);
        }
    }

    @RequestMapping(value = "/sysParamList")
    public String queryParamList(SysParam sysParam, Model model, PageParam pageParam){
        List<String> keyList = sysParamService.queryKeyList(sysParam);
        model.addAttribute("keyList", keyList);
        List<String> labelList = sysParamService.queryLabelList(sysParam);
        model.addAttribute("labelList", labelList);
        PageInfo<SysParam> sysParamPageInfo = sysParamService.queryListWithPage(sysParam, pageParam);
        model.addAttribute("sysParamPageInfo", sysParamPageInfo);
        return "admin/sys/sysParamList";
    }

    @RequestMapping(value = "/sysParamEdit")
    public String sysParamEdit(SysParam sysParam,Model model){
        return "admin/sys/sysParamEdit";
    }

    @RequestMapping(value = "/saveSysParam")
    public String saveSysParam(@Valid SysParam sysParam, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return sysParamEdit(sysParam, model);
        }
        Msg msg;
        try {
            sysParamService.save(sysParam);
            msg = new Msg(Msg.MsgType.ok, "保存系统参数成功");
        } catch (Exception e) {
            logger.error("保存系统参数失败");
            msg = new Msg(Msg.MsgType.remove, "保存系统参数失败");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/sysParamList";
    }

    @RequestMapping(value = "/deleteSysParam")
    public String deleteSysParam(@RequestParam String id,RedirectAttributes redirectAttributes){
        SysParam sysParam = new SysParam(id);
        Msg msg;
        if ("1".equals(sysParam.getId()) || "2".equals(sysParam.getId())){
            msg = new Msg(Msg.MsgType.remove, "删除系统默认参数不能删除");
        } else {
            try {
                sysParamService.delete(sysParam);
                msg = new Msg(Msg.MsgType.ok, "删除系统参数成功");
            } catch (Exception e) {
                logger.error("删除字典信息失败!", e);
                msg = new Msg(Msg.MsgType.remove, "删除系统参数失败");
            }
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/sysParamList";
    }
}
