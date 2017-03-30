package com.hncu.service.admin.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.sys.TeacherMapper;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.StringUtils;
import com.hncu.utils.SysParamUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理服务层
 */
@Service
public class TeacherService extends BaseService<TeacherMapper, TeacherInfo>{

    @Resource
    private UserService userService;

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

    public PageInfo<TeacherInfo> queryAnswerNotSelectWithPage(TeacherInfo teacherInfo, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TeacherInfo> resultList = mapper.queryAnswerNotSelectList(teacherInfo);
        String[] studentIds = null;
        User studentInfo = null;
        List<User> studentList = Lists.newArrayList();
        for (int i = 0; i < resultList.size(); i++) {
            studentIds = StringUtils.split(resultList.get(i).getStudentIds(), ",");
            int count = 0;
            if (studentIds != null){
                for (int j = 0; j < studentIds.length; j++) {
                    studentInfo = userService.queryById(studentIds[i]);
                    studentList.add(studentInfo);
                }
                for (int j = 0; j < studentList.size(); j++) {
                    if (studentList.get(j).getStudent().getClasses().equals(teacherInfo.getQueryClasses())){
                        count++;
                    }
                    resultList.get(i).setClassesStudentNumber(count);
                }
                if (resultList.get(i).getClassesStudentNumber() <= Integer.parseInt(SysParamUtil.getParamValue("sysAdviceAnswerTeacher"))){
                    resultList.get(i).setSysAdvice("系统建议设置");
                }
            } else {
                resultList.get(i).setClassesStudentNumber(count);
                resultList.get(i).setSysAdvice("系统建议设置");
            }
        }
        return new PageInfo<TeacherInfo>(resultList);
    }

    public List<TeacherInfo> queryTeacherIdList(){
        return mapper.queryTeacherIdList();
    }
}
