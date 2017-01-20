package com.hncu.utils;

import com.hncu.entity.Upload;
import com.hncu.entity.UploadParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 文件上传工具包
 */
public class UploadUtil {

    public static final String UPLOAD_SYS_PATH = "E://gpmsUpload/";

    public static UploadParam upload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        UploadParam uploadParam = new UploadParam();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    if (myFileName.trim() != "") {
                        String fileName = new Date().getTime() + file.getOriginalFilename();
                        String fileDir = UPLOAD_SYS_PATH + UserUtils.getCurrentUser().getUsername();
                        File dir = new File(fileDir);
                        if (!dir.exists()){
                            dir.mkdirs();
                        }
                        //String path = UPLOAD_SYS_PATH + UserUtils.getCurrentUser().getUsername() + "/" + fileName;
                        File localFile = new File(fileDir + "/" + fileName);
                        file.transferTo(localFile);
                        uploadParam.setUploadPath(fileDir + "/" + fileName);
                        uploadParam.setUploadFile(fileName);
                        uploadParam.setUploadFileOldName(myFileName);
                    }
                }
            }
        }
        return uploadParam;
    }
}
