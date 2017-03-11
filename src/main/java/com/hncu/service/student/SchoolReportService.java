package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.SchoolReportMapper;
import com.hncu.entity.SchoolReport;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;

/**
 * 成绩报告单服务层
 */
@Service
public class SchoolReportService extends BaseService<SchoolReportMapper, SchoolReport>{

    public SchoolReport querySchoolReportById(){
        return mapper.querySchoolReportById(UserUtils.getCurrentUser().getId(), UserUtils.getCurrentUser().getStudent().getSchoolReport().getReportId());
    }
}
