package com.hncu.service;

import com.hncu.dao.mapper.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 上传下载服务层
 */
@Service
public class UploadService {

    @Autowired
    private UploadMapper uploadMapper;

    public boolean uploadDate(String title, String issuer, String description, String receive, String uploadFile,String uploadPath,String uploadFileOldName){
        return uploadMapper.uploadDate(title,issuer,description,receive,uploadFile,uploadPath,uploadFileOldName)>0;
    }
}
