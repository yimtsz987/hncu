package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Dict;
import com.hncu.service.admin.sys.DictService;
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
 * 字典信息的控制器
 * byType byName
 */
@Controller
@RequestMapping(value = "/admin")
public class DictController extends BaseController{

    @Resource
    private DictService dictService;

    @ModelAttribute
    public Dict get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Dict();
        } else {
            return dictService.queryById(id);
        }
    }

    /**
     * 查询所有字典数据
     * @param dict
     * @param model
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/dictList")
    public String queryDictList (Dict dict, Model model, PageParam pageParam){
        List<String> typeList = dictService.queryTypeList(dict);
        model.addAttribute("typeList", typeList);

        PageInfo<Dict> pageInfoDict = dictService.queryListWithPage(dict, pageParam);
        model.addAttribute("pageInfoDict", pageInfoDict);
        return "admin/sys/dictList";
    }

    @RequestMapping(value = "/dictEdit")
    public String dictEdit(Dict dict, Model model){
        model.addAttribute("dict", dict);
        return "admin/sys/dictEdit";
    }

    @RequestMapping(value = "/saveDict")
    public String saveDict(@Valid Dict dict, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return dictEdit(dict, model);
        }
        Msg msg;
        try {
            dictService.save(dict);
            msg = new Msg(Msg.MsgType.success, "保存字典成功");
        } catch (Exception e){
            logger.error("保存字典失败");
            msg = new Msg(Msg.MsgType.error, "保存字典失败");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/dictList";
    }

    @RequestMapping(value = "/deleteDict")
    public  String deleteDict(@RequestParam String id,
                              @RequestParam String type,
                              RedirectAttributes redirectAttributes){
        Dict dict = new Dict(id);
        dict.setType(type);
        Msg msg;
        try {
            dictService.delete(dict);
            msg = new Msg(Msg.MsgType.success, "删除字典成功");
        } catch (Exception e) {
            logger.error("删除字典信息失败!", e);
            msg = new Msg(Msg.MsgType.error, "删除字典失败");
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/dictList";

    }
}
