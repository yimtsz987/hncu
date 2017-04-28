package com.hncu.service.student;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.student.ScheduleMapper;
import com.hncu.entity.Schedule;
import com.hncu.entity.UploadParam;
import com.hncu.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.hncu.common.BaseEntity.REPORT_FLAG_NO_PASS;
import static com.hncu.common.BaseEntity.REPORT_FLAG_NO_UPLOAD;
import static com.hncu.common.BaseEntity.STEP_FLAG_PASS;

/**
 * 进度计划表服务层
 */
@Service
public class ScheduleService extends BaseService<ScheduleMapper, Schedule> {

    @Override
    public Schedule queryById(String id) {
        Schedule schedule = mapper.queryById(id);
        if (StringUtils.isNotEmpty(schedule.getUploadFileOldName())){
            schedule.setCheckStr(MD5Util.string2MD5(schedule.getUploadFileOldName()));
        }
        return schedule;
    }

    @Override
    public PageInfo<Schedule> queryListWithPage(Schedule schedule, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Schedule> resultList = mapper.queryList(schedule);
        for (int i = 0; i < resultList.size(); i++) {
            Schedule schedule1 =  resultList.get(i);
            schedule1.setRemainingDate(DateUtils.distanceDays(schedule1.getEndDate()));
        }
        return new PageInfo<Schedule>(resultList);
    }

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class},readOnly = false)
    public void uploadScheduleReport(Schedule schedule, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(schedule.getUploadPath())){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            schedule.setUploadFile(uploadParam.getUploadFile());
            schedule.setUploadFileOldName(uploadParam.getUploadFileOldName());
            schedule.setUploadPath(uploadParam.getUploadPath());
            schedule.preInsertReport();
            mapper.uploadScheduleReport(schedule);
            mapper.stepFiveEnd(UserUtils.getCurrentUser());
        } else {
            UploadUtil.deleteFile(schedule.getUploadPath()); //先删除，后上传
            UploadParam uploadParam = UploadUtil.upload(request, response);
            schedule.setUploadFile(uploadParam.getUploadFile());
            schedule.setUploadFileOldName(uploadParam.getUploadFileOldName());
            schedule.setUploadPath(uploadParam.getUploadPath());
            schedule.preInsertReport();
            schedule.setTeacherAdvise(null);
            mapper.uploadScheduleReport(schedule);
        }

    }

    public Schedule queryScheduleBySort(String studentId, String sort){
        return mapper.queryScheduleBySort(studentId,sort);
    }
}
