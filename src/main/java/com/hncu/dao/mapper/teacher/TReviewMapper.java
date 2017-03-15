package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 评阅数据持久化操作接口层
 */
@Repository
public interface TReviewMapper extends BaseMapper<TeacherMarking>{

}
