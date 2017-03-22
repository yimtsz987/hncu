package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.service.teacher.TStudentService;
import com.hncu.service.teacher.TUnderstandingService;
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
 * 教师端 - 审题控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TUnderstandingController extends BaseController{

    @Resource
    private TUnderstandingService tUnderstandingService;

    @ModelAttribute
    public TeacherUnderstanding get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherUnderstanding();
        } else {
            return tUnderstandingService.queryById(id);
        }
    }

    @RequestMapping(value = "/understandingList")
    public String understandingList(TeacherUnderstanding teacherUnderstanding, Model model, PageParam pageParam){
        Understanding understanding = new Understanding();
        understanding.setTeacherId(UserUtils.getCurrentUser().getId());
        teacherUnderstanding.setUnderstanding(understanding);
        PageInfo<TeacherUnderstanding> teacherUnderstandingPageInfo = tUnderstandingService.queryStudentInfoListWithPage(teacherUnderstanding, pageParam);
        model.addAttribute("teacherUnderstandingPageInfo", teacherUnderstandingPageInfo);
        return "teacher/understandingList";
    }

    @RequestMapping(value = "/downloadStudentUnderstanding", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherUnderstanding teacherUnderstanding = tUnderstandingService.queryById(id);
        if (teacherUnderstanding != null){
            if (StringUtils.isNotEmpty(teacherUnderstanding.getUnderstanding().getUploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherUnderstanding.getUnderstanding().getUploadFileOldName()))){
                    File file = new File(teacherUnderstanding.getUnderstanding().getUploadPath());
                    String dfileName = teacherUnderstanding.getUnderstanding().getUploadFileOldName();
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

    @RequestMapping(value = "/understandingCheck")
    public String understandingCheck(TeacherUnderstanding teacherUnderstanding, Model model){
        model.addAttribute("teacherUnderstanding", teacherUnderstanding);
        return "teacher/understandingCheck";
    }

    @RequestMapping(value = "/saveUnderstandingCheck")
    public String saveUnderstandingCheck(@Valid TeacherUnderstanding teacherUnderstanding, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return understandingCheck(teacherUnderstanding, model);
        }
        try {
            tUnderstandingService.checkUnderstanding(teacherUnderstanding);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/teacher/understandingList";
    }
}
