package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.student.QuestionMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.student.MarkingService;
import com.hncu.service.student.QuestionService;
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
 * 教师批阅控制层
 */
@Controller
@RequestMapping(value = "/student")
public class QuestionController extends BaseController{

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    @ModelAttribute
    public Marking get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Marking();
        } else {
            return questionService.queryById(id);
        }
    }

    @RequestMapping(value = "/questionList")
    public String questionList(Marking marking, Model model, PageParam pageParam){
        marking.setStudentId(UserUtils.getCurrentUser().getId());
        PageInfo<Marking> questionPageInfo = questionService.queryListWithPage(marking, pageParam);
        model.addAttribute("questionPageInfo", questionPageInfo);
        return "student/questionList";
    }

    @RequestMapping(value = "/questionEdit")
    public String markingEdit(Marking marking,Model model){
            return "student/uploadQuestion";
    }

    @RequestMapping(value = "/uploadQuestion")
    public String uploadQuestion(@Valid Marking marking, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            markingEdit(marking, model);
        }
        try {
            questionService.uploadQuestion(marking,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/questionList";
    }

    @RequestMapping(value = "/questionInfo")
    public String questionInfo(Marking marking, Model model){
        return "student/questionInfo";
    }

    @RequestMapping(value = "/downloadStudentQuestion", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudentQuestion(@RequestParam String id, @RequestParam String studentCheckStr) throws IOException {
        Marking marking = questionService.queryById(id);
        if (marking != null){
            if (StringUtils.isNotBlank(marking.getSuploadFileOldName()) && StringUtils.isNotEmpty(studentCheckStr)){
                if (studentCheckStr.equals(MD5Util.string2MD5(marking.getSuploadFileOldName()))) {
                    File file = new File(marking.getSuploadPath());
                    String dfileName = marking.getSuploadFileOldName();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
                    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
                } else {
                    return null;
                }
            }else {
                return null;
            }
        } else {
            return null;
        }

    }

    @RequestMapping(value = "/downloadTeacherQuestion", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadTeacherQuestion(@RequestParam String id,  @RequestParam String teacherCheckStr) throws IOException {
        Marking marking = questionService.queryById(id);
        if (marking != null) {
            if (StringUtils.isNotBlank(marking.getSuploadFileOldName()) && StringUtils.isNotEmpty(teacherCheckStr)) {
                if (teacherCheckStr.equals(MD5Util.string2MD5(marking.getTuploadFileOldName()))) {
                    if (StringUtils.isNotBlank(marking.getTuploadFileOldName())) {
                        File file = new File(marking.getTuploadPath());
                        String dfileName = marking.getTuploadFileOldName();
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
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
