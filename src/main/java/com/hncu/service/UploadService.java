package com.hncu.service;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.UploadMapper;
import com.hncu.entity.Upload;
import com.hncu.entity.UploadParam;
import com.hncu.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 上传下载服务层
 */
@Service
public class UploadService extends BaseService<UploadMapper, Upload> {

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void uploadDate(Upload upload, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UploadParam uploadParam = UploadUtil.upload(request, response);
        upload.preInsert();
        upload.setReceive(upload.getRoleIds());
        upload.setUploadFile(uploadParam.getUploadFile());
        upload.setUploadFileOldName(uploadParam.getUploadFileOldName());
        upload.setUploadPath(uploadParam.getUploadPath());
        mapper.uploadDate(upload);
    }
}
