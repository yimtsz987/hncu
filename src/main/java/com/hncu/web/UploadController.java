package com.hncu.web;

import com.hncu.common.Msg;
import com.hncu.entity.Upload;
import com.hncu.service.UploadService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import com.hncu.web.admin.sys.UploadDateController;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * 上传文件控制层
 */
@Controller
@RequestMapping(value = "/file")
public class UploadController {

    /*  详细注释
      public String uploadData(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        // 创建一个通用的多部分解析器 ，用于解析SpringMVC的上下文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 解析request，判断是否为MultipartFile类型数据,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 重命名上传后的文件名
                        String fileName = new Date().getTime() + file.getOriginalFilename();

                     //定义上传路径
                     String path = "E://gpmsUpload/admin/"+fileName;
                     File localFile = new File(path); //
                     //把文件拷贝到本地：transferTo（gest）将上传文件写到服务器指定文件上
                     file.transferTo(localFile);
                        // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                        //String realPath = request.getSession().getServletContext() .getRealPath("/WEB-INF/upload/admin");
                        // 不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                       // FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
                    }
                }
            }
        }
        return "/success";
    }
    * */



    @Resource
    private UploadService uploadService;

    @Resource
    private UploadDateController uploadDateController;

    @RequestMapping(value = "/uploadData")
    public String uploadData(@Valid Upload upload, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
        if (br.hasErrors()){
            return uploadDateController.uploadDate(upload,model);
        }
        try {
            uploadService.uploadDate(upload,request,response);
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_OK, "上传成功！！"));
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msg",new Msg(Msg.MSG_TYPE_REMOVE, "上传失败！！"));
        }
        return "redirect:/admin/downloadList";
    }


    private  boolean checkFile(String fileName){
        boolean flag=false;
        String suffixList="xls,xlsx,jpg,gif,png,ico,bmp,jpeg";
        //获取文件后缀
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

        if(suffixList.contains(suffix.trim().toLowerCase())){
            flag=true;
        }
        return flag;
    }
}
