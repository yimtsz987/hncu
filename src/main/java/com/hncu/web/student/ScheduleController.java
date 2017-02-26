package com.hncu.web.student;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.PageParam;
import com.hncu.entity.Schedule;
import com.hncu.service.student.ScheduleService;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 进度计划表控制层
 */
@Controller
@RequestMapping(value = "/student")
public class ScheduleController extends BaseController{

    @Resource
    private ScheduleService scheduleService;

    @RequestMapping(value = "/scheduleList")
    public String scheduleList(Schedule schedule, Model model, PageParam pageParam){
        schedule.setStudentId(UserUtils.getCurrentUser().getId());
        PageInfo<Schedule> schedulePageInfo = scheduleService.queryListWithPage(schedule,pageParam);
        model.addAttribute("schedulePageInfo", schedulePageInfo);
        return "student/scheduleList";
    }
}
