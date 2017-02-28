package com.hncu.web.student;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.Schedule;
import com.hncu.service.student.OpenTitleService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 开题控制层
 */
@Controller
@RequestMapping(value = "/student")
public class OpenTitleController extends BaseController{

    @Resource
    private OpenTitleService openTitleService;

    @ModelAttribute
    public OpenTitle get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new OpenTitle();
        } else {
            return openTitleService.queryById(id);
        }
    }

    @RequestMapping(value = "/openTitlePage")
    public String openTilePage(OpenTitle openTitle, Model model){
        model.addAttribute("openTitle", openTitle);
        return "student/uploadOpenTitle";
    }

    @RequestMapping(value = "/uploadOpenTitleReport")
    public String uploadOpenTitleReport(@Valid OpenTitle openTitle, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return openTilePage(openTitle, model);
        }
        try {
            List<Schedule> scheduleList = new ArrayList<Schedule>();
            for (int i = 1; i < openTitle.getSort().length; i++){
                Schedule schedule = new Schedule();
                schedule.setSort(openTitle.getSort()[i]);
                schedule.setContent(openTitle.getContent()[i]);
                schedule.setStartDate(DateUtils.parse(openTitle.getStartDate()[i]));
                schedule.setEndDate(DateUtils.parse(openTitle.getEndDate()[i]));
                scheduleList.add(schedule);
            }
            openTitle.setScheduleList(scheduleList);
            openTitleService.openTitle(openTitle,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/openTitleInfo";
    }

    @RequestMapping(value = "/openTitleInfo")
    public String openTitleInfo(OpenTitle openTitle, Model model){
        OpenTitle openTitleInfo = openTitleService.queryById("1");
        model.addAttribute("openTitleInfo", openTitleInfo);
        return "student/openTitleInfo";
    }
}
