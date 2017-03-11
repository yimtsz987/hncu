package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Understanding;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 审题数据持久化操作接口层
 */
@Repository
public interface TUnderstandingMapper extends BaseMapper<Understanding>{

    /**
     * 查询学生审题报告
     * @param studentId
     * @return
     */
    Understanding queryByStudentId(@Param("studentId") String studentId);
}
