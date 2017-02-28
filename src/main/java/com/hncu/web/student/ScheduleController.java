package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Schedule;
import com.hncu.service.student.ScheduleService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.io.File;
import java.io.IOException;

/**
 * 进度计划表控制层
 */
@Controller
@RequestMapping(value = "/student")
public class ScheduleController extends BaseController{

    @Resource
    private ScheduleService scheduleService;

    @ModelAttribute
    public Schedule get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Schedule();
        } else {
            return scheduleService.queryById(id);
        }
    }

    @RequestMapping(value = "/scheduleList")
    public String scheduleList(Schedule schedule, Model model, PageParam pageParam){
        schedule.setStudentId(UserUtils.getCurrentUser().getId());
        PageInfo<Schedule> schedulePageInfo = scheduleService.queryListWithPage(schedule,pageParam);
        model.addAttribute("schedulePageInfo", schedulePageInfo);
        return "student/scheduleList";
    }

    @RequestMapping(value = "/uploadSchedulePage")
    public String uploadSchedulePage(Schedule schedule, Model model){
        model.addAttribute("schedule", schedule);
        return "student/uploadSchedule";
    }

    @RequestMapping(value = "/uploadScheduleReport")
    public String uploadScheduleReport(@Valid Schedule schedule, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
        return uploadSchedulePage(schedule, model);
        }
        try {
            scheduleService.uploadScheduleReport(schedule,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/scheduleList";
    }

    @RequestMapping(value = "/downloadSchedule", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id) throws IOException {
        Schedule schedule = scheduleService.queryById(id);
        if (StringUtils.isNotBlank(schedule.getUploadFileOldName())) {
            File file = new File(schedule.getUploadPath());
            String dfileName = schedule.getUploadFileOldName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }else {
            return null;
        }
    }
}
