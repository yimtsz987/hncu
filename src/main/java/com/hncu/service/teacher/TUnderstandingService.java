package com.hncu.service.teacher;

import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TUnderstandingMapper;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.utils.MD5Util;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 教师端 - 审题服务层
 */
@Service
public class TUnderstandingService extends BaseService<TUnderstandingMapper, TeacherUnderstanding>{

    @Override
    public TeacherUnderstanding queryById(String id) {
        TeacherUnderstanding teacherUnderstanding = mapper.queryById(id);
        teacherUnderstanding.getUnderstanding().setCheckStr(MD5Util.string2MD5(teacherUnderstanding.getUnderstanding().getUploadFileOldName()));
        return teacherUnderstanding;
    }

    @Transactional(readOnly = false)
    public void checkUnderstanding(TeacherUnderstanding teacherUnderstanding){
        if (teacherUnderstanding.getUnderstanding().getPassFlag().equals("2")){
            mapper.checkUnderstanding(teacherUnderstanding);
            mapper.updateStudentStep(new User(teacherUnderstanding.getUnderstanding().getStudentId()));
        } else {
            mapper.checkUnderstanding(teacherUnderstanding);
        }
    }
}
