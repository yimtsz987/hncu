package com.hncu.web.student;

import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.Schedule;
import com.hncu.service.student.OpenTitleService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        if (UserUtils.getCurrentUser().getStudent().getStep4().equals(openTitle.STEP_FLAG_NO)){
            return "student/uploadOpenTitle";
        } else {
            return "redirect:/student/openTitleInfo";
        }
    }

    @RequestMapping(value = "/uploadOpenTitleReport")
    public String uploadOpenTitleReport(@Valid OpenTitle openTitle, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
                                        String[] sort, String[] content, String[] startDate, String[] endDate){
        if (br.hasErrors()){
            return openTilePage(openTitle, model);
        }
        try {
            List<Schedule> scheduleList = new ArrayList<Schedule>();
            for (int i = 0; i < sort.length; i++){
                Schedule schedule = new Schedule();
                schedule.setSort(sort[i]);
                schedule.setContent(content[i]);
                schedule.setStartDate(DateUtils.parse(startDate[i]));
                schedule.setEndDate(DateUtils.parse(endDate[i]));
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
        OpenTitle openTitleInfo = openTitleService.queryOpenReportByStudentId();
        model.addAttribute("openTitleInfo", openTitleInfo);
        return "student/openTitleInfo";
    }

    @RequestMapping(value = "/uploadOpenTitleUpdate")
    public String uploadOpenTitleUpdate(OpenTitle openTitle, Model model){
        return "student/uploadOpenTitleUpdate";
    }

    @RequestMapping(value = "/uploadOpenTitleReportUpdate")
    public String uploadOpenTitleReportUpdate(@Valid OpenTitle openTitle, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return openTilePage(openTitle, model);
        }
        try {
            openTitleService.openTitle(openTitle,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/openTitleInfo";
    }
}
