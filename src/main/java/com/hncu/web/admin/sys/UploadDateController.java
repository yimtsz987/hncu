package com.hncu.web.admin.sys;

import com.hncu.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 上传资料控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class UploadDateController extends BaseController {

    @RequestMapping(value = "/uploadDate")
    public String uploadDate(){
        return "admin/sys/upload";
    }
}
