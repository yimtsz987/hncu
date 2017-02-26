package com.hncu.service.student;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.student.ScheduleMapper;
import com.hncu.entity.Schedule;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 进度计划表服务层
 */
@Service
public class ScheduleService extends BaseService<ScheduleMapper, Schedule> {

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
}
