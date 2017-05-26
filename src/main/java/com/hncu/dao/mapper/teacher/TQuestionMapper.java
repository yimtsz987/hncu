package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.TeacherQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 问题咨询教师端数据持久化操作接口层
 */
@Repository
public interface TQuestionMapper extends BaseMapper<TeacherQuestion> {

    /**
     * 回答问题
     * @param teacherMarking
     * @return
     */
    int solveQuestion(TeacherMarking teacherMarking);

    /**
     * 根据学生ID查询问题
     * @param studentId
     * @return
     */
    List<Marking> queryByStudentId(@Param("studentId") String studentId);

    /**
     * 根据学教师ID查询问题
     * @param teacherId
     * @return
     */
    List<Marking> queryByTeacherId(@Param("teacherId") String teacherId);
}
