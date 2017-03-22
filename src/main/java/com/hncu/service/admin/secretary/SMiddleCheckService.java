package com.hncu.service.admin.secretary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.secretary.SMiddleCheckMapper;
import com.hncu.dao.mapper.teacher.TMiddleCheckMapper;
import com.hncu.entity.TeacherMiddleCheck;
import com.hncu.entity.User;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教师端 - 中期检查服务层
 */
@Service
public class SMiddleCheckService extends BaseService<SMiddleCheckMapper, TeacherMiddleCheck>{

    @Override
    public TeacherMiddleCheck queryById(String id) {
        TeacherMiddleCheck teacherMiddleCheck = mapper.queryById(id);
        teacherMiddleCheck.getMiddleCheck().setCheckStr(MD5Util.string2MD5(teacherMiddleCheck.getMiddleCheck().getUploadFileOldName()));
        return teacherMiddleCheck;
    }

    @Transactional(readOnly = false)
    public void checkMiddleCheck(TeacherMiddleCheck teacherMiddleCheck) {
        teacherMiddleCheck.getMiddleCheck().setCheckId(UserUtils.getCurrentUser().getId());
        mapper.checkMiddleCheck(teacherMiddleCheck);
    }

    public boolean updateStudentStep(User user){
        return mapper.updateStudentStep(user) > 0;
    }
}
