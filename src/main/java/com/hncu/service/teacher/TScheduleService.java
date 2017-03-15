package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.TOpenTitleMapper;
import com.hncu.dao.mapper.teacher.TScheduleMapper;
import com.hncu.entity.Schedule;
import com.hncu.entity.TeacherOpenTitle;
import com.hncu.entity.TeacherSchedule;
import com.hncu.entity.User;
import com.hncu.utils.DateUtils;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教师端 - 开题服务层
 */
@Service
public class TScheduleService extends BaseService<TScheduleMapper, TeacherSchedule>{

    @Override
    public PageInfo<TeacherSchedule> queryListWithPage(TeacherSchedule teacherSchedule, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TeacherSchedule> resultList = mapper.queryList(teacherSchedule);
        for (int i = 0; i < resultList.size(); i++) {
            TeacherSchedule teacherSchedule1 =  resultList.get(i);
            teacherSchedule1.getSchedule().setRemainingDate(DateUtils.distanceDays(teacherSchedule1.getSchedule().getEndDate()));
        }
        return new PageInfo<TeacherSchedule>(resultList);
    }

    @Override
    public TeacherSchedule queryById(String id) {
        TeacherSchedule teacherSchedule = mapper.queryById(id);
        teacherSchedule.getSchedule().setCheckStr(MD5Util.string2MD5(teacherSchedule.getSchedule().getUploadFileOldName()));
        return teacherSchedule;
    }

    @Transactional(readOnly = false)
    public void checkSchedule(TeacherSchedule teacherSchedule){
        if (teacherSchedule.getSchedule().getSort().equals(teacherSchedule.getSchedule().getLastSort())
                && teacherSchedule.getSchedule().getReportFlag().equals("3")){
            mapper.checkSchedule(teacherSchedule);
            mapper.updateStudentStep(new User(teacherSchedule.getSchedule().getStudentId()));
        } else {
            mapper.checkSchedule(teacherSchedule);
        }
    }
}
