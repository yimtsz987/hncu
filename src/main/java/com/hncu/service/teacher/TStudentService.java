package com.hncu.service.teacher;

import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TStudentMapper;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.TeacherAndStudent;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师学生信息服务层
 */
@Service
public class TStudentService extends BaseService<TStudentMapper, TeacherAndStudent>{

    @Resource
    private UserService userService;

    public List<User> queryStudentList(String teacherId, String sysYear){
        List<User> userList = Lists.newArrayList();
        TeacherAndStudent teacherAndStudent =  mapper.queryByTeacherId(teacherId, sysYear);
        User user;
        for (String id : StringUtils.split(teacherAndStudent.getStudentIds(),",")){
            user = userService.queryById(id);
            userList.add(user);
        }
        return userList;
    }

    @Transactional(readOnly = false)
    public void deleteStudent(TeacherAndStudent teacherAndStudent, User user){
        mapper.deleteStudent(teacherAndStudent);
        mapper.deleteTeacherId(user);
    }

    public List<User> queryByReviewId(String reviewTeacherId, String sysYear){
        List<User> userList = Lists.newArrayList();
        TeacherAndStudent teacherAndStudent =  mapper.queryByReviewId(reviewTeacherId, sysYear);
        User user;
        for (String id : StringUtils.split(teacherAndStudent.getStudentIds(),",")){
            user = userService.queryById(id);
            userList.add(user);
        }
        return userList;
    }
}
