package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.teacher.ReviewWorkService;
import com.hncu.service.teacher.TOpenTitleService;
import com.hncu.service.teacher.TReviewService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * 教师端 - 评阅控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class ReviewWorkController extends BaseController{

    @Resource
    private ReviewWorkService reviewWorkService;

    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherMarking get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherMarking();
        } else {
            return reviewWorkService.queryById(id);
        }
    }

    @RequestMapping(value = "/reviewWorkStudentList")
    public String reviewWorkStudentList(TeacherMarking teacherMarking, Model model, PageParam pageParam){
        Marking marking = new Marking();
        marking.setReviewTeacherId(UserUtils.getCurrentUser().getId());
        teacherMarking.setMarking(marking);
        PageInfo<TeacherMarking> teacherReviewPageInfo = reviewWorkService.queryStudentInfoListWithPage(teacherMarking, pageParam);
        model.addAttribute("teacherReviewPageInfo", teacherReviewPageInfo);
        return "teacher/reviewWorkStudentList";
    }

    @RequestMapping(value = "/reviewWorkInfoList")
    public String reviewWorkInfoList(TeacherMarking teacherMarking, Model model, PageParam pageParam){
        Marking marking = new Marking();
        marking.setReviewTeacherId(UserUtils.getCurrentUser().getId());
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
        PageInfo<TeacherMarking> teacherReviewPageInfo = reviewWorkService.queryListWithPage(teacherMarking, pageParam);
        User studentInfo = userService.queryById(marking.getStudentId());
        model.addAttribute("teacherReviewPageInfo", teacherReviewPageInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/reviewWorkInfoList";
    }

    @RequestMapping(value = "/reviewWorkCheck")
    public String reviewWorkCheck(TeacherMarking teacherMarking, Model model){
        return "teacher/reviewWorkCheck";
    }

    @RequestMapping(value = "/downloadStudentReviewWork", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudent(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = reviewWorkService.queryById(id);
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

    @RequestMapping(value = "/downloadTeacherReviewWork", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadTeacher(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = reviewWorkService.queryById(id);
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

    @RequestMapping(value = "/saveReviewWorkCheck")
    public String saveReviewWorkCheck(@Valid TeacherMarking teacherMarking, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return reviewWorkCheck(teacherMarking, model);
        }
        try {
            reviewWorkService.reviewWorkCheck(teacherMarking, request, response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "提交成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "提交失败！！"));
        }
        return "redirect:/teacher/reviewWorkInfoList?marking.studentId=" + teacherMarking.getUser().getId();
    }

    @RequestMapping(value = "/updateStudentReviewStep")
    public String updateStudentReviewStep(@RequestParam String studentId, RedirectAttributes redirectAttributes){
        User user = userService.queryById(studentId);
        if (user != null){
            if (user.getStudent().getTeacherId().equals(UserUtils.getCurrentUser().getId())){
                try {
                    reviewWorkService.updateStudentReviewStep(user);
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
        return "redirect:/teacher/reviewWorkStudentList";
    }
}
