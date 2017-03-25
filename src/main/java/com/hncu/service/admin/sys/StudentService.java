package com.hncu.service.admin.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseMapper;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.sys.StudentMapper;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.User;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public PageInfo<StudentInfo> queryStudentListByClassesWithPage (StudentInfo studentInfo, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<StudentInfo> resultList = mapper.queryStudentListByClasses(studentInfo);
        return new PageInfo<StudentInfo>(resultList);
    }
}
