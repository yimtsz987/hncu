package com.hncu.service.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.StudentMapper;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.User;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生管理服务层
 */
@Service
public class StudentService extends BaseService<StudentMapper, StudentInfo> {

    @Transactional
    public void saveStudentInfo(StudentInfo studentInfo){
        if(StringUtils.isBlank(studentInfo.getId())) {
            studentInfo.preInsert();
            studentInfo.preInsertStudent();
            mapper.insertUserInfo(studentInfo);
            studentInfo.setsId(studentInfo.getId());
            mapper.insertExpandInfo(studentInfo);
            mapper.insertStudentRole(studentInfo.getId());
        } else {
            studentInfo.preUpdate();
            mapper.updateUserInfo(studentInfo);
            mapper.updateExpandInfo(studentInfo);
            System.out.print("待定");
        }
    }

    @Override
    @Transactional
    public boolean delete(StudentInfo studentInfo) {
        mapper.deleteUserInfo(studentInfo);
        mapper.deleteExpandInfo(studentInfo);
        int res = mapper.deleteStudentRole(studentInfo);
        return res>0;
    }
}
