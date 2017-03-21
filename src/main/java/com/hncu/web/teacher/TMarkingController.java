package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.teacher.TMarkingService;
import com.hncu.service.teacher.TOpenTitleService;
import com.hncu.service.teacher.TScheduleService;
import com.hncu.service.teacher.TStudentService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.SysParamUtil;
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
import java.util.List;

/**
 * 教师端 - 开题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TMarkingController extends BaseController{

    @Resource
    private TMarkingService tMarkingService;


    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherMarking get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherMarking();
        } else {
            return tMarkingService.queryById(id);
        }
    }

    @RequestMapping(value = "/markingList")
    public String markingList(TeacherMarking teacherMarking, Model model, PageParam pageParam){
        Marking marking = new Marking();
        marking.setTeacherId(UserUtils.getCurrentUser().getId());
        teacherMarking.setMarking(marking);
        PageInfo<TeacherMarking> teacherMarkingPageInfo = tMarkingService.queryStudentInfoListWithPage(teacherMarking, pageParam);
        model.addAttribute("teacherMarkingPageInfo", teacherMarkingPageInfo);
        return "teacher/markingList";
    }


    @RequestMapping(value = "/markingInfoList")
    public String markingInfoList(TeacherMarking teacherMarking, Model model, PageParam pageParam){
        Marking marking = new Marking();
        marking.setTeacherId(UserUtils.getCurrentUser().getId());
        marking.setStudentId(teacherMarking.getMarking().getStudentId());
        if(teacherMarking.getMarking() != null){
            if (StringUtils.isNotBlank(teacherMarking.getMarking().getState())){
                marking.setState(teacherMarking.getMarking().getState());
            }
            if (teacherMarking.getMarking().getSort() != null){
                marking.setSort(teacherMarking.getMarking().getSort());
            }
            if (StringUtils.isNotBlank(teacherMarking.getMarking().getSuploadFileOldName())){
                marking.setSuploadFileOldName(teacherMarking.getMarking().getSuploadFileOldName());
            }
        }
        teacherMarking.setMarking(marking);
        PageInfo<TeacherMarking> teacherMarkingPageInfo = tMarkingService.queryListWithPage(teacherMarking, pageParam);
        User studentInfo = userService.queryById(marking.getStudentId());
        model.addAttribute("teacherMarkingPageInfo", teacherMarkingPageInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/markingInfoList";
    }

    @RequestMapping(value = "/downloadStudentMarking", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudent(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = tMarkingService.queryById(id);
        if (teacherMarking != null){
            if (StringUtils.isNotEmpty(teacherMarking.getMarking().getSuploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherMarking.getMarking().getSuploadFileOldName()))){
                    File file = new File(teacherMarking.getMarking().getSuploadPath());
                    String dfileName = teacherMarking.getMarking().getSuploadFileOldName();
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

    @RequestMapping(value = "/downloadTeacherMarking", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadTeacher(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = tMarkingService.queryById(id);
        if (teacherMarking != null){
            if (StringUtils.isNotEmpty(teacherMarking.getMarking().getTuploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherMarking.getMarking().getTuploadFileOldName()))){
                    File file = new File(teacherMarking.getMarking().getTuploadPath());
                    String dfileName = teacherMarking.getMarking().getTuploadFileOldName();
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

    @RequestMapping(value = "/markingCheck")
    public String markingCheck(TeacherMarking teacherMarking, Model model){
        model.addAttribute("teacherMarking", teacherMarking);
        return "teacher/markingCheck";
    }

    @RequestMapping(value = "/saveMarkingCheck")
    public String saveMarkingCheck(@Valid TeacherMarking teacherMarking, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return markingCheck(teacherMarking, model);
        }
        try {
            tMarkingService.checkMarking(teacherMarking, request, response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "提交成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "提交失败！！"));
        }
        return "redirect:/teacher/markingInfoList";
    }

    @RequestMapping(value = "/updateStudentStep")
    public String updateStudentStep(@RequestParam String studentId, RedirectAttributes redirectAttributes){
        User user = userService.queryById(studentId);
        if (user != null){
            if (user.getStudent().getTeacherId().equals(UserUtils.getCurrentUser().getId())){
                try {
                    tMarkingService.updateStudentStep(user);
                    redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "更新成功！！"));
                }catch (Exception e){
                    redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "更新失败！！"));
                }
            } else {
                redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "操作有误！！"));
            }
        } else {
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "查询不到改学生信息！！"));
        }
        return "redirect:/teacher/markingInfoList?marking.studentId="+studentId;
    }
}
