package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.MiddleCheckMapper;
import com.hncu.entity.MiddleCheck;
import com.hncu.entity.UploadParam;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 中期检查服务层
 */
@Service
public class MiddleCheckService extends BaseService<MiddleCheckMapper, MiddleCheck> {

    public MiddleCheck queryMiddleCheckByParamId(String studentId, String paramId){
        return mapper.queryMiddleCheckByParamId(studentId, paramId);
    }

    @Transactional(readOnly = false)
    public void uploadMiddleCheck(MiddleCheck middleCheck, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (queryMiddleCheckByParamId(UserUtils.getCurrentUser().getId(), middleCheck.getParamId()) == null){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            middleCheck.setUploadFile(uploadParam.getUploadFile());
            middleCheck.setUploadFileOldName(uploadParam.getUploadFileOldName());
            middleCheck.setUploadPath(uploadParam.getUploadPath());
            middleCheck.preInsertMiddleCheck();
            mapper.uploadMiddleCheck(middleCheck);
            mapper.stepSevenEnd(UserUtils.getCurrentUser());
        } else {
            UploadParam uploadParam = UploadUtil.upload(request, response);
            middleCheck.setUploadFile(uploadParam.getUploadFile());
            middleCheck.setUploadFileOldName(uploadParam.getUploadFileOldName());
            middleCheck.setUploadPath(uploadParam.getUploadPath());
            middleCheck.preInsertMiddleCheck();
            mapper.uploadMiddleCheckUpdate(middleCheck);
        }
    }
}
