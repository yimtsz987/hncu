package com.hncu.dao.mapper.admin.secretary;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherMiddleCheck;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 中期检查数据持久化操作接口层
 */
@Repository
public interface SMiddleCheckMapper extends BaseMapper<TeacherMiddleCheck>{

    /**
     * 中期检查审核
     * @param teacherMiddleCheck
     * @return
     */
    int checkMiddleCheck(TeacherMiddleCheck teacherMiddleCheck);

    /**
     * 中期检查完成
     * @param user
     * @return
     */
    int updateStudentStep(User user);
}
