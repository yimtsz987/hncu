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

    TeacherAndStudent queryByTeacherId(@Param("teacherId") String teacherId, @Param("sysYear") String sysYear);


    int deleteStudent(TeacherAndStudent teacherAndStudent);

    int deleteTeacherId(User user);
}
