package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.teacher.TMiddleCheckService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 教师端 - 评阅控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TReviewController extends BaseController{

    @Resource
    private TReviewService tReviewService;

    @Resource
    private TOpenTitleService tOpenTitleService;

    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherMarking get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherMarking();
        } else {
            return tReviewService.queryById(id);
        }
    }

    @RequestMapping(value = "/reviewList")
    public String reviewList(TeacherOpenTitle teacherOpenTitle, Model model, PageParam pageParam){
        OpenTitle openTitle = new OpenTitle();
        openTitle.setTeacherId(UserUtils.getCurrentUser().getId());
        if(teacherOpenTitle.getOpenTitle() != null){
            if (StringUtils.isNotBlank(teacherOpenTitle.getOpenTitle().getReportFlag())){
                openTitle.setReportFlag(teacherOpenTitle.getOpenTitle().getReportFlag());
            }
        }
        teacherOpenTitle.setOpenTitle(openTitle);
        PageInfo<TeacherOpenTitle> teacherReviewPageInfo = tOpenTitleService.queryListWithPage(teacherOpenTitle, pageParam);
        model.addAttribute("teacherReviewPageInfo", teacherReviewPageInfo);
        return "teacher/reviewList";
    }


    @RequestMapping(value = "/reviewInfoList")
    public String reviewInfoList(TeacherMarking teacherMarking, Model model, PageParam pageParam){
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
        PageInfo<TeacherMarking> teacherReviewPageInfo = tReviewService.queryListWithPage(teacherMarking, pageParam);
        User studentInfo = userService.queryById(marking.getStudentId());
        model.addAttribute("teacherReviewPageInfo", teacherReviewPageInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/reviewInfoList";
    }

    @RequestMapping(value = "/reviewCheck")
    public String reviewCheck(TeacherMarking teacherMarking, Model model){
        return "teacher/reviewCheck";
    }

    @RequestMapping(value = "/downloadStudentReview", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudent(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = tReviewService.queryById(id);
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

    @RequestMapping(value = "/downloadTeacherReview", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadTeacher(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMarking teacherMarking = tReviewService.queryById(id);
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

}
