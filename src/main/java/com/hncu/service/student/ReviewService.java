package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.MarkingMapper;
import com.hncu.dao.mapper.student.ReviewMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.UploadParam;
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
public class ReviewService extends BaseService<ReviewMapper, Marking> {

    @Transactional(readOnly = false)
    public void uploadReviewReport(Marking marking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (UserUtils.getCurrentUser().getStudent().getStep8().equals(marking.STEP_FLAG_NO)){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(1);
            mapper.uploadReviewReport(marking);
            mapper.stepEightEnd(UserUtils.getCurrentUser());
        } else {
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(marking.getSort() + 1);
            mapper.uploadReviewReport(marking);
        }
    }

    public Marking queryLastReviewSort(){
        return mapper.queryLastReviewSort(UserUtils.getCurrentUser().getId());
    }
}
