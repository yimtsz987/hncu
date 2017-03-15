package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.teacher.TMiddleCheckService;
import com.hncu.service.teacher.TOpenTitleService;
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
 * 教师端 - 开题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TMiddleCheckController extends BaseController{

    @Resource
    private TMiddleCheckService tMiddleCheckService;

    @Resource
    private TOpenTitleService tOpenTitleService;

    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherMiddleCheck get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherMiddleCheck();
        } else {
            return tMiddleCheckService.queryById(id);
        }
    }

    @RequestMapping(value = "/middleCheckList")
    public String middleCheckList(TeacherOpenTitle teacherOpenTitle, Model model, PageParam pageParam){
        OpenTitle openTitle = new OpenTitle();
        openTitle.setTeacherId(UserUtils.getCurrentUser().getId());
        if(teacherOpenTitle.getOpenTitle() != null){
            if (StringUtils.isNotBlank(teacherOpenTitle.getOpenTitle().getReportFlag())){
                openTitle.setReportFlag(teacherOpenTitle.getOpenTitle().getReportFlag());
            }
        }
        teacherOpenTitle.setOpenTitle(openTitle);
        PageInfo<TeacherOpenTitle> teacherMiddleCheckPageInfo = tOpenTitleService.queryListWithPage(teacherOpenTitle, pageParam);
        model.addAttribute("teacherMiddleCheckPageInfo", teacherMiddleCheckPageInfo);
        return "teacher/middleCheckList";
    }


    @RequestMapping(value = "/middleCheckInfoList")
    public String middleCheckInfoList(TeacherMiddleCheck teacherMiddleCheck, Model model, PageParam pageParam){
        MiddleCheck middleCheck = new MiddleCheck();
        middleCheck.setTeacherId(UserUtils.getCurrentUser().getId());
        middleCheck.setStudentId(teacherMiddleCheck.getMiddleCheck().getStudentId());
        if(teacherMiddleCheck.getMiddleCheck() != null){
            if (StringUtils.isNotBlank(teacherMiddleCheck.getMiddleCheck().getState())){
                middleCheck.setState(teacherMiddleCheck.getMiddleCheck().getState());
            }
            if (StringUtils.isNotEmpty(teacherMiddleCheck.getMiddleCheck().getParamId())){
                middleCheck.setParamId(teacherMiddleCheck.getMiddleCheck().getParamId());
            }
            if (StringUtils.isNotEmpty(teacherMiddleCheck.getMiddleCheck().getParamName())){
                middleCheck.setParamName(teacherMiddleCheck.getMiddleCheck().getParamName());
            }
        }
        teacherMiddleCheck.setMiddleCheck(middleCheck);
        PageInfo<TeacherMiddleCheck> teacherMiddleCheckPageInfo = tMiddleCheckService.queryListWithPage(teacherMiddleCheck, pageParam);
        User studentInfo = userService.queryById(middleCheck.getStudentId());
        model.addAttribute("teacherMiddleCheckPageInfo", teacherMiddleCheckPageInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/middleCheckInfoList";
    }

    @RequestMapping(value = "/downloadMiddleCheck", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMiddleCheck teacherMiddleCheck = tMiddleCheckService.queryById(id);
        if (teacherMiddleCheck != null){
            if (StringUtils.isNotEmpty(teacherMiddleCheck.getMiddleCheck().getUploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherMiddleCheck.getMiddleCheck().getUploadFileOldName()))){
                    File file = new File(teacherMiddleCheck.getMiddleCheck().getUploadPath());
                    String dfileName = teacherMiddleCheck.getMiddleCheck().getUploadFileOldName();
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
