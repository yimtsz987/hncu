package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.TeacherAndStudent;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 学生信息数据持久化操作接口层
 */
@Repository
public interface TStudentMapper extends BaseMapper<TeacherAndStudent>{

    /**
     * 通过指导老师查询学生
     * @param teacherId
     * @param sysYear
     * @return
     */
    TeacherAndStudent queryByTeacherId(@Param("teacherId") String teacherId, @Param("sysYear") String sysYear);

    /**
     * 通过评阅教师查询学生
     * @param reviewTeacherId
     * @param sysYear
     * @return
     */
    TeacherAndStudent queryByReviewId(@Param("reviewTeacherId") String reviewTeacherId, @Param("sysYear") String sysYear);

    /**
     * 删除学生
     * @param teacherAndStudent
     * @return
     */
    int deleteStudent(TeacherAndStudent teacherAndStudent);

    /**
     * 删除教师id
     * @param user
     * @return
     */
    int deleteTeacherId(User user);

    /**
     * 查询评阅学生信息
     * @param teacherAndStudent
     * @return
     */
    TeacherAndStudent queryReviewStudentInfoList(TeacherAndStudent teacherAndStudent);
}
