package com.hncu.web.teacher;

import com.github.pagehelper.PageInfo;
import com.hncu.common.PageParam;
import com.hncu.entity.Notice;
import com.hncu.service.NoticeService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统管理员首页行控制层
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherHomeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping(value = "/home")
    public String home(HttpServletRequest request, Model model){
        String loginIp = RequestUtil.getRemoteAddr(request);
        PageInfo<Notice> noticePageInfo = noticeService.queryTeacherNoticeListWithPage(new Notice(), new PageParam());
        model.addAttribute("noticePageInfo", noticePageInfo);
        model.addAttribute("loginIp", loginIp);
        model.addAttribute("nowDate", DateUtils.getDateTime());
        return "teacher/home";
    }
}
