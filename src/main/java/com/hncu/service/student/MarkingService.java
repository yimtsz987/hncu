package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.MarkingMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.Schedule;
import com.hncu.entity.UploadParam;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 教师批阅服务层
 */
@Service
public class MarkingService extends BaseService<MarkingMapper, Marking> {

    @Override
    public Marking queryById(String id) {
        Marking marking = mapper.queryById(id);
        marking.setStudentCheckStr(MD5Util.string2MD5(marking.getSuploadFileOldName()));
        if (StringUtils.isNotEmpty(marking.getTuploadFileOldName())){
            marking.setTeacherCheckStr(MD5Util.string2MD5(marking.getTuploadFileOldName()));
        }
        return marking;
    }

    @Transactional(readOnly = false)
    public void uploadMarkingReport(Marking marking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (UserUtils.getCurrentUser().getStudent().getStep6().equals(marking.STEP_FLAG_NO)){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(1);
            mapper.uploadMarkingReport(marking);
            mapper.stepSixEnd(UserUtils.getCurrentUser());
        } else {
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(marking.getSort() + 1);
            mapper.uploadMarkingReport(marking);
        }
    }

    public Marking queryLastMarkingSort(){
        return mapper.queryLastMarkingSort(UserUtils.getCurrentUser().getId());
    }
}
