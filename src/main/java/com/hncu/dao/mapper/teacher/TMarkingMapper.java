package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 开题数据持久化操作接口层
 */
@Repository
public interface TMarkingMapper extends BaseMapper<TeacherMarking>{

    /**
     * 审核批阅
     * @param teacherMarking
     * @return
     */
    int checkMarking(TeacherMarking teacherMarking);

    /**
     * 更新学生步骤
     * @param user
     * @return
     */
    int updateStudentStep(User user);
}
