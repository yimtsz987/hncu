package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.UnderstandingMapper;
import com.hncu.entity.Understanding;
import com.hncu.entity.UploadParam;
import com.hncu.entity.User;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 审题服务层
 */
@Service
public class UnderstandingService extends BaseService<UnderstandingMapper, Understanding>{

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class},readOnly = false)
    public void uploadReport(Understanding understanding,HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(understanding.getPassFlag()) || "0".equals(understanding.getPassFlag())){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            understanding.preInsert();
            understanding.setUploadFile(uploadParam.getUploadFile());
            understanding.setUploadFileOldName(uploadParam.getUploadFileOldName());
            understanding.setUploadPath(uploadParam.getUploadPath());
            mapper.uploadReport(understanding);
            mapper.stepThreeEnd(UserUtils.getCurrentUser());
        } else if ("1".equals(understanding.getPassFlag())){
            UploadUtil.deleteFile(understanding.getUploadPath()); //先删除，后上传
            UploadParam uploadParam = UploadUtil.upload(request, response);
            understanding.preInsert();
            understanding.setUploadFile(uploadParam.getUploadFile());
            understanding.setUploadFileOldName(uploadParam.getUploadFileOldName());
            understanding.setUploadPath(uploadParam.getUploadPath());
            understanding.setTeacherAdvise(null);
            mapper.uploadReportAlter(understanding);
        }
    }

    public Understanding queryUnderstandingByStudentId(String studentId){
        return mapper.queryUnderstandingByStudentId(studentId);
    }

}
