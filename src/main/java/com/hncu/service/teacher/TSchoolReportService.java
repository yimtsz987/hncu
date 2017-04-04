package com.hncu.service.teacher;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TSchoolReportMapper;
import com.hncu.entity.SchoolReport;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 上传成绩服务层
 */
@Service
public class TSchoolReportService extends BaseService<TSchoolReportMapper, SchoolReport>{

    @Resource
    private UserService userService;

    public void saveSchoolReport(SchoolReport schoolReport){
        if (StringUtils.isEmpty(schoolReport.getId())){
            if (schoolReport.getPassFlag().equals("0")){
                mapper.insertSecondAnswer(schoolReport);
            }
            schoolReport.preInsertSchoolReport();
            User studentInfo = userService.queryById(schoolReport.getStudentId());
            schoolReport.setReportId(studentInfo.getStudent().getNode()+ DateUtils.formatDateTimeDefault(schoolReport.getDateTime()));
            mapper.uploadSchoolReport(schoolReport);
        } else {
            if (schoolReport.getPassFlag().equals("0")){
                mapper.insertSecondAnswer(schoolReport);
            } else {
                mapper.deleteSecondAnswer(schoolReport);
            }
            mapper.updateSchoolReport(schoolReport);
        }
    }
}
