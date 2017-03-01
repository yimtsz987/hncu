package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.Marking;
import com.hncu.service.student.MarkingService;
import com.hncu.service.student.ReviewService;
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
 * 批阅控制层
 */
@Controller
@RequestMapping(value = "/student")
public class ReviewController extends BaseController{

    @Resource
    private ReviewService reviewService;

    @ModelAttribute
    public Marking get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new Marking();
        } else {
            return reviewService.queryById(id);
        }
    }

    @RequestMapping(value = "/reviewList")
    public String reviewList(Marking marking, Model model, PageParam pageParam){
        marking.setStudentId(UserUtils.getCurrentUser().getId());
        PageInfo<Marking> reviewListPageInfo = reviewService.queryListWithPage(marking, pageParam);
        model.addAttribute("reviewListPageInfo", reviewListPageInfo);
        return "student/reviewList";
    }

    @RequestMapping(value = "/reviewEdit")
    public String reviewEdit(Marking marking,Model model){
        if (UserUtils.getCurrentUser().getStudent().getStep6().equals(marking.STEP_FLAG_NO)){
            return "student/uploadReview";
        } else {
            Marking marking1 = reviewService.queryLastReviewSort();
            if (marking1 == null){
                marking.setSort(1);
                return "student/uploadReview";
            } else {
                marking.setSort(marking1.getSort());
                return "student/uploadReview";
            }
        }
    }

    @RequestMapping(value = "/uploadReview")
    public String uploadReview(@Valid Marking marking, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            reviewEdit(marking, model);
        }
        try {
            reviewService.uploadReviewReport(marking,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/student/reviewList";
    }

    @RequestMapping(value = "/reviewInfo")
    public String reviewInfo(Marking marking, Model model){
        return "student/reviewInfo";
    }

    @RequestMapping(value = "/downloadStudentReview", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadStudentReview(@RequestParam String id) throws IOException {
        Marking marking = reviewService.queryById(id);
        if (StringUtils.isNotBlank(marking.getSuploadFileOldName())){
            File file = new File(marking.getSuploadPath());
            String dfileName = marking.getSuploadFileOldName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/downloadTeacherReview", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadTeacherReview(@RequestParam String id) throws IOException {
        Marking marking = reviewService.queryById(id);
        if (StringUtils.isNotBlank(marking.getTuploadFileOldName())){
            File file = new File(marking.getTuploadPath());
            String dfileName = marking.getTuploadFileOldName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }else {
            return null;
        }
    }
}
