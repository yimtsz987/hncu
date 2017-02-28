package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.OpenMapper;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.Understanding;
import com.hncu.entity.UploadParam;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 开题服务层
 */
@Service
public class OpenTitleService extends BaseService<OpenMapper, OpenTitle> {

    @Transactional(readOnly = false)
    public void openTitle(OpenTitle openTitle, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(openTitle.getId())){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            openTitle.setUploadFile(uploadParam.getUploadFile());
            openTitle.setUploadFileOldName(uploadParam.getUploadFileOldName());
            openTitle.setUploadPath(uploadParam.getUploadPath());
            openTitle.preInsertOpenTitleReport();
            mapper.uploadOpenReport(openTitle);
            mapper.insertSchedule(openTitle);
            mapper.stepFourEnd(UserUtils.getCurrentUser());
        } else {
            UploadUtil.deleteFile(openTitle.getUploadPath()); //先删除，后上传
            UploadParam uploadParam = UploadUtil.upload(request, response);
            openTitle.setUploadFile(uploadParam.getUploadFile());
            openTitle.setUploadFileOldName(uploadParam.getUploadFileOldName());
            openTitle.setUploadPath(uploadParam.getUploadPath());
            openTitle.preInsertOpenTitleReport();
            mapper.uploadOpenReportUpdate(openTitle);
        }
    }
}
