package com.hncu.web.student;

import com.hncu.common.BaseController;
import com.hncu.entity.SchoolReport;
import com.hncu.service.student.SchoolReportService;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 成绩报告单控制层
 */
@Controller
@RequestMapping(value = "/student")
public class SchoolReportController extends BaseController {

    @Resource
    private SchoolReportService schoolReportService;

    @ModelAttribute
    public SchoolReport get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new SchoolReport();
        } else {
            return schoolReportService.queryById(id);
        }
    }

    @RequestMapping(value = "/schoolReport")
    public String schoolReportPage(Model model){
        SchoolReport schoolReport = schoolReportService.querySchoolReportById();
        model.addAttribute("schoolReport", schoolReport);
        return "student/schoolReport";
    }
}
