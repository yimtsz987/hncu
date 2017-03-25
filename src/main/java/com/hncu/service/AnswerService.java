package com.hncu.service;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.AnswerMapper;
import com.hncu.entity.Answer;
import org.springframework.stereotype.Service;

/**
 * 答辩服务层
 */
@Service
public class AnswerService extends BaseService<AnswerMapper, Answer>{

    public Answer queryByClasses(String answerClasses){
        return mapper.queryByClasses(answerClasses);
    }
}
