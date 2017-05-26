package com.hncu.service;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.AnswerMapper;
import com.hncu.entity.Answer;
import com.hncu.utils.DateUtils;
import org.springframework.stereotype.Service;

/**
 * 答辩服务层
 */
@Service
public class AnswerService extends BaseService<AnswerMapper, Answer>{

    public Answer queryByClasses(String answerClasses){
        Answer answer = mapper.queryByClasses(answerClasses);
        if (answer.getAnswerTime() != null){
            answer.setCountDown((int) DateUtils.distanceDays(answer.getAnswerTime()));
        }
        return answer;
    }

    @Override
    public Answer queryById(String id) {
        Answer answer = super.queryById(id);
        if (answer.getAnswerTime() != null){
            answer.setCountDown((int) DateUtils.distanceDays(answer.getAnswerTime()));
        }
        return answer;
    }

    public Answer queryByLeaderId(String leaderId){
        return mapper.queryByLeaderId(leaderId);
    }
}
