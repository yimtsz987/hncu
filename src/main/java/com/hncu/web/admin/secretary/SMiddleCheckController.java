package com.hncu.web.admin.secretary;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.secretary.SMiddleCheckMapper;
import com.hncu.entity.MiddleCheck;
import com.hncu.entity.TeacherMiddleCheck;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.service.admin.secretary.SMiddleCheckService;
import com.hncu.service.teacher.TMiddleCheckService;
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
@RequestMapping(value = "/secretary")
public class SMiddleCheckController extends BaseController{

    @Resource
    private SMiddleCheckService sMiddleCheckService;

    @Resource
    private UserService userService;

    @ModelAttribute
    public TeacherMiddleCheck get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherMiddleCheck();
        } else {
            return sMiddleCheckService.queryById(id);
        }
    }

    @RequestMapping(value = "/middleCheckList")
    public String middleCheckList(TeacherMiddleCheck teacherMiddleCheck, Model model, PageParam pageParam){
        MiddleCheck middleCheck = new MiddleCheck();
        middleCheck.setTeacherId(UserUtils.getCurrentUser().getId());
        teacherMiddleCheck.setMiddleCheck(middleCheck);
        PageInfo<TeacherMiddleCheck> teacherMiddleCheckPageInfo = sMiddleCheckService.queryStudentInfoListWithPage(teacherMiddleCheck, pageParam);
        model.addAttribute("teacherMiddleCheckPageInfo", teacherMiddleCheckPageInfo);
        return "admin/secretary/middleCheckList";
    }


    @RequestMapping(value = "/middleCheckInfoList")
    public String middleCheckInfoList(TeacherMiddleCheck teacherMiddleCheck, Model model, PageParam pageParam){
        MiddleCheck middleCheck = new MiddleCheck();
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
        PageInfo<TeacherMiddleCheck> teacherMiddleCheckPageInfo = sMiddleCheckService.queryListWithPage(teacherMiddleCheck, pageParam);
        User studentInfo = userService.queryById(middleCheck.getStudentId());
        model.addAttribute("teacherMiddleCheckPageInfo", teacherMiddleCheckPageInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "admin/secretary/middleCheckInfoList";
    }

    @RequestMapping(value = "/downloadStudentMiddleCheck", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudent(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherMiddleCheck teacherMiddleCheck = sMiddleCheckService.queryById(id);
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

    @RequestMapping(value = "/middleCheckCheck")
    public String middleCheckCheck(TeacherMiddleCheck teacherMiddleCheck, Model model){
        model.addAttribute("teacherMiddleCheck", teacherMiddleCheck);
        return "admin/secretary/middleCheckCheck";
    }

    @RequestMapping(value = "/saveMiddleCheck")
    public String saveMiddleCheck(@Valid TeacherMiddleCheck teacherMiddleCheck, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return middleCheckCheck(teacherMiddleCheck, model);
        }
        try {
            sMiddleCheckService.checkMiddleCheck(teacherMiddleCheck);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "提交成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "提交失败！！"));
        }
        return "redirect:/secretary/middleCheckInfoList?middleCheck.studentId=" + teacherMiddleCheck.getUser().getId();
    }

    @RequestMapping(value = "/updateStudentStep")
    public String updateStudentStep(@RequestParam String studentId, RedirectAttributes redirectAttributes){
        User user = userService.queryById(studentId);
        if (user != null){
                try {
                    sMiddleCheckService.updateStudentStep(user);
                    redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "更新成功！！"));
                }catch (Exception e){
                    redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "更新失败！！"));
                }
        } else {
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "查询不到改学生信息！！"));
        }
        return "redirect:/secretary/middleCheckList";
    }
}
