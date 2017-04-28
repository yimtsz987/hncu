package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.*;
import com.hncu.service.AnswerService;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.StudentService;
import com.hncu.service.student.SchoolReportService;
import com.hncu.service.teacher.TSchoolReportService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 成绩上传控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TSchoolReportController extends BaseController{

    @Resource
    private TSchoolReportService tSchoolReportService;

    @Resource
    private StudentService studentService;

    @Resource
    private AnswerService answerService;

    @Resource
    private ClassesService classesService;

    @Resource
    private SchoolReportService schoolReportService;

    @ModelAttribute
    public SchoolReport get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new SchoolReport();
        } else {
            return tSchoolReportService.queryById(id);
        }
    }

    @RequestMapping(value = "/schoolReportStudentList")
    public String queryStudentListByClasses(StudentInfo studentInfo, Model model, PageParam pageParam){
        Answer answer = answerService.queryByLeaderId(UserUtils.getCurrentUser().getId());
        if (answer != null){
            studentInfo.setClasses(answer.getAnswerClasses());
            Classes classesInfo = classesService.queryByClasses(studentInfo.getClasses());
            PageInfo<StudentInfo> studentInfoPageInfo = studentService.queryStudentListByClassesWithPage(studentInfo, pageParam);
            model.addAttribute("studentInfoPageInfo", studentInfoPageInfo);
            model.addAttribute("classesInfo", classesInfo);
        }
        return "teacher/schoolReportStudentList";
    }

    @RequestMapping(value = "/uploadSchoolReportEdit")
    public String uploadSchoolReportEdit(@RequestParam String studentId, Model model){
        StudentInfo studentInfo = studentService.queryById(studentId);
        model.addAttribute("studentInfo", studentInfo);
        return "teacher/uploadSchoolReport";
    }

    @RequestMapping(value = "/saveSchoolReport")
    public String saveSchoolReport(@Valid SchoolReport schoolReport, BindingResult br, Model model, RedirectAttributes redirectAttributes){
        if (br.hasErrors()){
            return uploadSchoolReportEdit(schoolReport.getStudentId(), model);
        }
        try {
            tSchoolReportService.saveSchoolReport(schoolReport);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "成绩提交成功！！"));
        }catch (Exception e){
            logger.error("成绩提交失败！！", e);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "成绩提交失败！！"));
        }
        return "redirect:/teacher/schoolReportStudentList";
    }

    @RequestMapping(value = "/querySchoolReport")
    public String querySchoolReport(@RequestParam String studentId, @RequestParam String reportId, Model model){
        SchoolReport schoolReport = schoolReportService.querySchoolReportById(studentId, reportId);
        model.addAttribute("schoolReport", schoolReport);
        return "teacher/schoolReport";
    }
}
