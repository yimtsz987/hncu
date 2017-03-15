package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherSchedule;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 分期报告数据持久化操作接口层
 */
@Repository
public interface TScheduleMapper extends BaseMapper<TeacherSchedule>{

    /**
     * 开题报告审核
     * @param teacherSchedule
     * @return
     */
    int checkSchedule(TeacherSchedule teacherSchedule);

    /**
     * 更新学生步骤
     * @param user
     * @return
     */
    int updateStudentStep(User user);
}
