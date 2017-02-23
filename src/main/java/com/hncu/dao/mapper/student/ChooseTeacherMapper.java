package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师管理数据持久化接口层
 */
@Repository
public interface ChooseTeacherMapper extends BaseMapper<TeacherInfo> {

    /**
     * 选择老师
     * @param teacherInfo
     * @return
     */
    int chooseTeacher(TeacherInfo teacherInfo);

    /**
     * 步骤一结束
     * @param user
     * @return
     */
    int stepOneEnd(User user);

    /**
     * 修改学生所选教师ID
     * @param teacherId
     * @param sId
     * @return
     */
    int teacherIdEdit(@Param("teacherId") String teacherId,@Param("sId") String sId);
}
