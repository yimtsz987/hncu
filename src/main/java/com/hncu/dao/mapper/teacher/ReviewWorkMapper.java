package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 评阅工作数据持久化操作接口层
 */
@Repository
public interface ReviewWorkMapper extends BaseMapper<TeacherMarking>{

    /**
     * 评阅工作审核
     * @param teacherMarking
     * @return
     */
    int reviewWorkCheck(TeacherMarking teacherMarking);

    /**
     * 完成评阅步骤
     * @param user
     * @return
     */
    int updateStudentReviewStep(User user);
}
