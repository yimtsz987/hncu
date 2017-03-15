package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 审题数据持久化操作接口层
 */
@Repository
public interface TUnderstandingMapper extends BaseMapper<TeacherUnderstanding>{

    /**
     * 审批学生审题报告
     * @param teacherUnderstanding
     * @return
     */
    int checkUnderstanding(TeacherUnderstanding teacherUnderstanding);

    /**
     * 更新学生步骤
     * @param user
     * @return
     */
    int updateStudentStep(User user);
}
