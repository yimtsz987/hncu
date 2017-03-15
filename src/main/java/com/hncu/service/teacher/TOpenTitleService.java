package com.hncu.service.teacher;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TOpenTitleMapper;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.User;
import com.hncu.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教师端 - 开题服务层
 */
@Service
public class TOpenTitleService extends BaseService<TOpenTitleMapper, TeacherOpenTitle>{

    @Override
    public TeacherOpenTitle queryById(String id) {
        TeacherOpenTitle teacherOpenTitle = mapper.queryById(id);
        teacherOpenTitle.getOpenTitle().setCheckStr(MD5Util.string2MD5(teacherOpenTitle.getOpenTitle().getUploadFileOldName()));
        return teacherOpenTitle;
    }

    @Transactional(readOnly = false)
    public void checkOpenTitle(TeacherOpenTitle teacherOpenTitle){
        if (teacherOpenTitle.getOpenTitle().getReportFlag().equals("2")){
            mapper.checkOpenTitle(teacherOpenTitle);
            mapper.updateStudentStep(new User(teacherOpenTitle.getOpenTitle().getStudentId()));
        } else {
            mapper.checkOpenTitle(teacherOpenTitle);
        }
    }
}
