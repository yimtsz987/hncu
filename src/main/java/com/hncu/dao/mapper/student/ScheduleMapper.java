package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Schedule;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 进度计划表数据持久化操作接口层
 */
@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {

    /**
     * 上传进度计划报告
     * @param schedule
     * @return
     */
    int uploadScheduleReport(Schedule schedule);

    /**
     * 插入计划进度数量
     * @param scheduleNum
     * @param studentId
     * @return
     */
    int scheduleNum(@Param("scheduleNum") String scheduleNum, @Param("sId") String studentId);

    /**
     * 查询最后一个计划
     * @param studentId
     * @param lastSort
     * @return
     */
    Schedule queryLastSchedule(@Param("studentId") String studentId, @Param("lastSort") String lastSort);

    /**
     * 步骤五完成
     * @param user
     * @return
     */
    int stepFiveEnd(User user);

    /**
     * 通过排序查询计划
     * @param studentId
     * @param sort
     * @return
     */
    Schedule queryScheduleBySort(@Param("studentId") String studentId, @Param("sort") String sort);
}
