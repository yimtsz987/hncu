package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.TeacherMapper;
import com.hncu.entity.TeacherInfo;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教师管理服务层
 */
@Service
public class TeacherService extends BaseService<TeacherMapper, TeacherInfo>{

    @Transactional
    public void saveTeacherInfo(TeacherInfo teacherInfo){
        if (StringUtils.isBlank(teacherInfo.getId())){
            teacherInfo.preInsert();
            teacherInfo.preInsertTeacher();
            mapper.insertUserInfo(teacherInfo);
            teacherInfo.settId(teacherInfo.getId());
            mapper.insertExpandInfo(teacherInfo);
            mapper.insertTeacherRole(teacherInfo.gettId());
        } else {
            teacherInfo.preUpdate();
            mapper.updateUserInfo(teacherInfo);
            mapper.updateExpandInfo(teacherInfo);
        }
    }

    @Override
    @Transactional
    public boolean delete(TeacherInfo teacherInfo) {
        mapper.deleteUserInfo(teacherInfo);
        mapper.deleteExpandInfo(teacherInfo);
        int res = mapper.deleteTeacherRole(teacherInfo);
        return res>0;
    }
}
