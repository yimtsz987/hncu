package com.hncu.web.student;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.service.student.UnderstandingService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.hncu.common.BaseEntity.STEP_FLAG_CHECK;
import static com.hncu.common.BaseEntity.STEP_FLAG_NO;

/**
 * 审题控制层
 */
@Controller
@RequestMapping(value = "/student")
public class UnderstandingController extends BaseController {

    @Resource
    private UnderstandingService understandingService;

    @ModelAttribute
    public Understanding get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Understanding();
        } else {
            return understandingService.queryById(id);
        }
    }

    @RequestMapping(value = "/understanding")
    public String understandingPage(Understanding understanding, Model model){
        if (STEP_FLAG_NO.equals(UserUtils.getCurrentUser().getStudent().getStep3())){
            return "/student/uploadUnderstanding";
        }else if (STEP_FLAG_CHECK.equals(UserUtils.getCurrentUser().getStudent().getStep3())  && "1".equals(understanding.getPassFlag())){
            model.addAttribute("understanding", understanding);
            return "/student/uploadUnderstanding";
        } else {
            return "redirect:/student/understandingInfo";
        }
    }

    @RequestMapping(value = "/uploadReport")
    public String uploadReport(@Valid Understanding understanding, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return understandingPage(understanding, model);
        }
        try {
            understandingService.uploadReport(understanding,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/understandingInfo";
    }

    @RequestMapping(value = "/understandingInfo")
    public String understandingInfo(Model model){
        Understanding understandingInfo = understandingService.queryUnderstandingByStudentId(UserUtils.getCurrentUser().getId());
        model.addAttribute("understandingInfo", understandingInfo);
        return "/student/understandingInfo";
    }
}
