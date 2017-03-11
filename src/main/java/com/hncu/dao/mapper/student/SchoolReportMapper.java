package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.SchoolReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 成绩报告单数据持久化操作接口层
 */
@Repository
public interface SchoolReportMapper extends BaseMapper<SchoolReport>{

    /**
     * 根据学生id和成绩单编号查询成绩
     * @param studentId
     * @param reportId
     * @return
     */
    SchoolReport querySchoolReportById(@Param("studentId") String studentId, @Param("reportId") String reportId);
}
