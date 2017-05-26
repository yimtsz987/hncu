package com.hncu.web;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.ReportAndFile;
import com.hncu.service.ReportAndFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 报告及文件控制层
 */
@Controller
@RequestMapping(value = "/data")
public class ReportAndFileController extends BaseController {

    @Resource
    private ReportAndFileService reportAndFileService;

    @RequestMapping(value = "/reportAndFileList")
    public String reportAndFileList(ReportAndFile reportAndFile, Model model, PageParam pageParam){
        PageInfo<ReportAndFile> reportAndFilePageInfo = reportAndFileService.queryListWithPage(reportAndFile, pageParam);
        model.addAttribute("studentId",reportAndFile.getStudentId());
        model.addAttribute("reportAndFilePageInfo", reportAndFilePageInfo);
        return "reportAndFileList";
    }
}
