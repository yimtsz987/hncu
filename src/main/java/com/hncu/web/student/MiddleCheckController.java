package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.MiddleCheck;
import com.hncu.entity.MiddleCheckParam;
import com.hncu.service.MiddleCheckParamService;
import com.hncu.service.student.MiddleCheckService;
import com.hncu.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
 * 中期检查控制层
 */
@Controller
@RequestMapping(value = "/student")
public class MiddleCheckController extends BaseController{

    @Resource
    private MiddleCheckParamService middleCheckParamService;

    @Resource
    private MiddleCheckService middleCheckService;

    @RequestMapping(value = "/middleCheckList")
    public String middleCheckList(MiddleCheckParam middleCheckParam, Model model, PageParam pageParam){
        PageInfo<MiddleCheckParam> middleCheckParamPageInfo = middleCheckParamService.queryListWithPage(middleCheckParam, pageParam);
        model.addAttribute("middleCheckParamPageInfo", middleCheckParamPageInfo);
        return "student/middleCheckList";
    }

    @RequestMapping(value = "/uploadMiddleEditDoc")
    public String uploadMiddleCheckDoc(MiddleCheck middleCheck, Model model){
        return "student/uploadMiddleDoc";
    }

    @RequestMapping(value = "/uploadMiddleEditZip")
    public String uploadMiddleCheckZip(MiddleCheck middleCheck, Model model){
        return "student/uploadMiddleZip";
    }

    @RequestMapping(value = "/uploadMiddleCheck")
    public String uploadMiddleCheck(@Valid MiddleCheck middleCheck, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            uploadMiddleCheckDoc(middleCheck, model);
        }
        try {
            middleCheckService.uploadMiddleCheck(middleCheck,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/middleCheckList";
    }

    @RequestMapping(value = "/downloadMiddleCheck", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadMiddleCheck(@RequestParam String id) throws IOException {
        MiddleCheck middleCheck = middleCheckService.queryById(id);
        if (StringUtils.isNotBlank(middleCheck.getUploadFileOldName())){
            File file = new File(middleCheck.getUploadPath());
            String dfileName = middleCheck.getUploadFileOldName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }else {
            return null;
        }
    }
}
