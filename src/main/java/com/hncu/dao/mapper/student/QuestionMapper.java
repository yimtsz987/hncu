package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Marking;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 问题咨询数据持久化操作接口层
 */
@Repository
public interface QuestionMapper extends BaseMapper<Marking> {

    /**
     * 上传问题
     * @param marking
     * @return
     */
    int uploadQuestion(Marking marking);

    /**
     * 根据学生ID查询问题
     * @param studentId
     * @return
     */
    List<Marking> queryByStudentId(@Param("studentId") String studentId);
}
