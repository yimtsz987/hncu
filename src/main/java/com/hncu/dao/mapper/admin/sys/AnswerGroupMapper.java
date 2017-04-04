package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Answer;
import com.hncu.entity.TeacherInfo;
import org.springframework.stereotype.Repository;

/**
 * 答辩分组数据持久化操作接口层
 */
@Repository
public interface AnswerGroupMapper extends BaseMapper<Answer> {

    /**
     * 设置答辩组长
     * @param teacherInfo
     * @return
     */
    int updateLeader(TeacherInfo teacherInfo);

    /**
     * 更新组长答辩分组标记信息
     * @param teacherInfo
     * @return
     */
    int updateAnswerLeaderInfo(TeacherInfo teacherInfo);

    /**
     * 更新答辩分组标记信息
     * @param teacherInfo
     * @return
     */
    int updateAnswerInfo(TeacherInfo teacherInfo);

    /**
     * 撤除答辩组长
     * @param teacherInfo
     * @return
     */
    int deleteLeader(TeacherInfo teacherInfo);

    /**
     * 删除答辩分组标记信息
     * @param teacherInfo
     * @return
     */
    int deleteAnswerInfo(TeacherInfo teacherInfo);

    /**
     * 通过班级号查询分组信息
     * @param answer
     * @return
     */
    Answer queryByClasses(Answer answer);

    /**
     * 更新答辩组教师
     * @param answer
     * @return
     */
    int updateAnswerTeacher(Answer answer);

    /**
     * 更新答辩地址和答辩时间
     * @param answer
     * @return
     */
    int updateAnswerTimeInfo(Answer answer);
}
