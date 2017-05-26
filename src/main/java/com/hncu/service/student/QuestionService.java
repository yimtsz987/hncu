package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.MarkingMapper;
import com.hncu.dao.mapper.student.QuestionMapper;
import com.hncu.entity.Marking;
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
import java.util.List;

/**
 * 问题咨询服务层
 */
@Service
public class QuestionService extends BaseService<QuestionMapper, Marking> {

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
    public void uploadQuestion(Marking marking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Marking> markingList = mapper.queryByStudentId(UserUtils.getCurrentUser().getId());
        if (markingList.size() == 0){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(1);
            mapper.uploadQuestion(marking);
        } else {
            UploadParam uploadParam = UploadUtil.upload(request, response);
            marking.setSuploadFile(uploadParam.getUploadFile());
            marking.setSuploadFileOldName(uploadParam.getUploadFileOldName());
            marking.setSuploadPath(uploadParam.getUploadPath());
            marking.preInsertMarkingReportStudent();
            marking.setSort(markingList.get(markingList.size()-1).getSort() + 1);
            mapper.uploadQuestion(marking);
        }
    }

    public List<Marking> queryQuestionListByStudentId(String studentId){
        return mapper.queryByStudentId(studentId);
    }
}
