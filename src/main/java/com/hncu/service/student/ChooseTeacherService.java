package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.ChooseTeacherMapper;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选择教师服务层
 */
@Service
public class ChooseTeacherService extends BaseService<ChooseTeacherMapper, TeacherInfo>{

    public TeacherInfo queryTeacherInfoById(TeacherInfo teacherInfo){
        return mapper.queryTeacherInfoById(teacherInfo);
    }

    /**
     * 选择函数
     * @param teacherInfo
     * @param user
     */
    @Transactional(readOnly = false)
    public void choose(TeacherInfo teacherInfo,User user){
        teacherInfo.setStudentSum(teacherInfo.getStudentSum() + 1);
        if (StringUtils.isNotEmpty(teacherInfo.getStudentIds())){
            teacherInfo.setStudentIds(teacherInfo.getStudentIds() + user.getId() + ",");
        } else {
            teacherInfo.setStudentIds(user.getId() + ",");
        }
        mapper.chooseTeacher(teacherInfo);
        mapper.teacherIdEdit(teacherInfo.getId(),user.getId());
        mapper.stepOneEnd(user);
    }

    @Transactional(readOnly = false)
    public void chooseTeacher(TeacherInfo teacherInfo,User user){
        boolean flag = true;
        if (StringUtils.isNotBlank(teacherInfo.getStudentIds())){
            for (String id : StringUtils.split(teacherInfo.getStudentIds(),",")){
                if (user.getId().equals(id)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                choose(teacherInfo,user);
            }
        }else {
            choose(teacherInfo,user);
        }
    }


}
