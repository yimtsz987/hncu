package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.ScoreScaleMapper;
import com.hncu.entity.ScoreScale;
import org.springframework.stereotype.Service;

/**
 * 成绩比例服务层
 */
@Service
public class ScoreScaleService extends BaseService<ScoreScaleMapper, ScoreScale> {

    public ScoreScale queryByKey(ScoreScale scoreScale){
        return mapper.queryByKey(scoreScale);
    }
}
