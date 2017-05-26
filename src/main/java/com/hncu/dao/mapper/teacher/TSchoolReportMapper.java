package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.SchoolReport;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 成绩上传数据持久化操作接口层
 */
@Repository
public interface TSchoolReportMapper extends BaseMapper<SchoolReport> {

    /**
     * 上传成绩
     * @param schoolReport
     * @return
     */
    int uploadSchoolReport(SchoolReport schoolReport);

    /**
     * 更新成绩
     * @param schoolReport
     * @return
     */
    int updateSchoolReport(SchoolReport schoolReport);

    /**
     * 插入二次答辩学生信息
     * @param schoolReport
     * @return
     */
    int insertSecondAnswer(SchoolReport schoolReport);

    /**
     * 删除二次答辩学生信息
     * @param schoolReport
     * @return
     */
    int deleteSecondAnswer(SchoolReport schoolReport);

    /**
     * 更新学生步骤
     * @param user
     * @return
     */
    int updateStudentStep(User user);

    /**
     * 根据学生ID查询成绩报告
     * @param studentId
     * @return
     */
    SchoolReport queryByStudentId(@Param("studentId") String studentId);
}
