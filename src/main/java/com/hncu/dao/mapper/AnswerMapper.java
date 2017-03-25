package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 答辩数据持久化操作接口层
 */
@Repository
public interface AnswerMapper extends BaseMapper<Answer>{

    /**
     * 通过班级查询答辩分组
     * @param answerClasses
     * @return
     */
    Answer queryByClasses(@Param("answerClasses") String answerClasses);
}
