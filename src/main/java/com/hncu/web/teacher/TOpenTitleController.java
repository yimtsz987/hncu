package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.service.teacher.TOpenTitleService;
import com.hncu.service.teacher.TUnderstandingService;
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
public class TOpenTitleController extends BaseController{

    @Resource
    private TOpenTitleService tOpenTitleService;

    @ModelAttribute
    public TeacherOpenTitle get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new TeacherOpenTitle();
        } else {
            return tOpenTitleService.queryById(id);
        }
    }

    @RequestMapping(value = "/openTitleList")
    public String openTitleList(TeacherOpenTitle teacherOpenTitle, Model model, PageParam pageParam){
        OpenTitle openTitle = new OpenTitle();
        openTitle.setTeacherId(UserUtils.getCurrentUser().getId());
        if(teacherOpenTitle.getOpenTitle() != null){
            if (StringUtils.isNotBlank(teacherOpenTitle.getOpenTitle().getReportFlag())){
                openTitle.setReportFlag(teacherOpenTitle.getOpenTitle().getReportFlag());
            }
        }
        teacherOpenTitle.setOpenTitle(openTitle);
        PageInfo<TeacherOpenTitle> teacherOpenTitlePageInfo = tOpenTitleService.queryListWithPage(teacherOpenTitle, pageParam);
        model.addAttribute("teacherOpenTitlePageInfo", teacherOpenTitlePageInfo);
        return "teacher/openTitleList";
    }

    @RequestMapping(value = "/downloadStudentOpenTitle", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String checkStr) throws IOException {
        TeacherOpenTitle teacherOpenTitle = tOpenTitleService.queryById(id);
        if (teacherOpenTitle != null){
            if (StringUtils.isNotEmpty(teacherOpenTitle.getOpenTitle().getUploadFileOldName())){
                if (checkStr.equals(MD5Util.string2MD5(teacherOpenTitle.getOpenTitle().getUploadFileOldName()))){
                    File file = new File(teacherOpenTitle.getOpenTitle().getUploadPath());
                    String dfileName = teacherOpenTitle.getOpenTitle().getUploadFileOldName();
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

    @RequestMapping(value = "/openTitleCheck")
    public String openTitleCheck(TeacherOpenTitle teacherOpenTitle, Model model){
        model.addAttribute("teacherOpenTitle", teacherOpenTitle);
        return "teacher/openTitleCheck";
    }

    @RequestMapping(value = "/saveOpenTitleCheck")
    public String saveOpenTitleCheck(@Valid TeacherOpenTitle teacherOpenTitle, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return openTitleCheck(teacherOpenTitle, model);
        }
        try {
            tOpenTitleService.checkOpenTitle(teacherOpenTitle);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "提交成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "提交失败！！"));
        }
        return "redirect:/teacher/openTitleList";
    }
}
