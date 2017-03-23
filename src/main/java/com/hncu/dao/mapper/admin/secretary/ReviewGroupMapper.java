package com.hncu.dao.mapper.admin.secretary;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherAndStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评阅分组数据持久化操作接口层
 */
@Repository
public interface ReviewGroupMapper extends BaseMapper<TeacherAndStudent>{

    /**
     * 评阅随机分组
     * @return
     */
    int randomGroup(TeacherAndStudent teacherAndStudent);
}
