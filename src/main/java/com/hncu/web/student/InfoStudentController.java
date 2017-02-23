package com.hncu.web.student;

import com.hncu.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 个人信息控制层  -  学生端
 */
@Controller
@RequestMapping(value = "/student")
public class InfoStudentController extends BaseController{

    @RequestMapping(value = "/info")
    public String studentInfo(){
        return "student/info";
    }
}
