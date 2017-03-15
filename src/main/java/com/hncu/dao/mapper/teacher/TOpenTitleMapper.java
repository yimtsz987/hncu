package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 开题数据持久化操作接口层
 */
@Repository
public interface TOpenTitleMapper extends BaseMapper<TeacherOpenTitle>{

    /**
     * 开题报告审核
     * @param teacherOpenTitle
     * @return
     */
    int checkOpenTitle(TeacherOpenTitle teacherOpenTitle);

    /**
     * 更新学生步骤
     * @param user
     * @return
     */
    int updateStudentStep(User user);
}
