package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 审题数据持久化接口层
 */
@Repository
public interface UnderstandingMapper extends BaseMapper<Understanding> {

    /**
     * 提交审题报告
     * @param understanding
     * @return
     */
    int uploadReport(Understanding understanding);

    /**
     * 步骤三完成
     * @param user
     * @return
     */
    int stepThreeEnd(User user);

    /**
     * 查询学生开题报告
     * @param studentId
     * @return
     */
    Understanding queryUnderstandingByStudentId(@Param("studentId") String studentId);

    /**
     * 修改开题报告
     * @param understanding
     * @return
     */
    int uploadReportAlter(Understanding understanding);
}
