package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.Schedule;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.TeacherSchedule;
import com.hncu.service.teacher.TOpenTitleService;
import com.hncu.service.teacher.TScheduleService;
import com.hncu.utils.MD5Util;
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
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * 教师端 - 开题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TScheduleController extends BaseController{

    @Resource
    private TScheduleService tScheduleService;

    @Resource
    private TOpenTitleService tOpenTitleService;

    @ModelAttribute
    public TeacherSchedule get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherSchedule();
        } else {
            return tScheduleService.queryById(id);
        }
    }

    @RequestMapping(value = "/scheduleList")
    public String scheduleList(TeacherOpenTitle teacherOpenTitle, Model model, PageParam pageParam){
        OpenTitle openTitle = new OpenTitle();
        openTitle.setTeacherId(UserUtils.getCurrentUser().getId());
        if(teacherOpenTitle.getOpenTitle() != null){
            if (StringUtils.isNotBlank(teacherOpenTitle.getOpenTitle().getReportFlag())){
                openTitle.setReportFlag(teacherOpenTitle.getOpenTitle().getReportFlag());
            }
        }
        teacherOpenTitle.setOpenTitle(openTitle);
        PageInfo<TeacherOpenTitle> teacherSchedulePageInfo = tOpenTitleService.queryListWithPage(teacherOpenTitle, pageParam);
        model.addAttribute("teacherSchedulePageInfo", teacherSchedulePageInfo);
        return "teacher/scheduleList";
    }


    @RequestMapping(value = "/scheduleInfoList")
    public String scheduleInfoList(TeacherSchedule teacherSchedule, Model model, PageParam pageParam){
        Schedule schedule = new Schedule();
        schedule.setTeacherId(UserUtils.getCurrentUser().getId());
        schedule.setReportId(teacherSchedule.getSchedule().getReportId());
        schedule.setStudentId(teacherSchedule.getSchedule().getStudentId());
        if(teacherSchedule.getSchedule() != null){
            if (StringUtils.isNotBlank(teacherSchedule.getSchedule().getReportFlag())){
                schedule.setReportFlag(teacherSchedule.getSchedule().getReportFlag());
            }
        }
        teacherSchedule.setSchedule(schedule);
        PageInfo<TeacherSchedule> teacherSchedulePageInfo = tScheduleService.queryListWithPage(teacherSchedule, pageParam);
        model.addAttribute("teacherSchedulePageInfo", teacherSchedulePageInfo);
        return "teacher/scheduleInfoList";
    }

    @RequestMapping(value = "/downloadStudentSchedule", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherSchedule teacherSchedule = tScheduleService.queryById(id);
        if (teacherSchedule != null){
            if (StringUtils.isNotEmpty(teacherSchedule.getSchedule().getUploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherSchedule.getSchedule().getUploadFileOldName()))){
                    File file = new File(teacherSchedule.getSchedule().getUploadPath());
                    String dfileName = teacherSchedule.getSchedule().getUploadFileOldName();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
                    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/scheduleCheck")
    public String scheduleCheck(TeacherSchedule teacherSchedule, Model model){
        model.addAttribute("teacherSchedule", teacherSchedule);
        return "teacher/scheduleCheck";
    }

    @RequestMapping(value = "/saveScheduleCheck")
    public String saveScheduleCheck(@Valid TeacherSchedule teacherSchedule, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return scheduleCheck(teacherSchedule, model);
        }
        try {
            tScheduleService.checkSchedule(teacherSchedule);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "提交成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "提交失败！！"));
        }
        return "redirect:/teacher/scheduleInfoList?schedule.reportId=" + teacherSchedule.getSchedule().getReportId() + "&schedule.studentId=" + teacherSchedule.getSchedule().getStudentId();
    }
}
