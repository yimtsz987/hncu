package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.ScoreScale;
import org.springframework.stereotype.Repository;

/**
 * 成绩比例数据持久化操作接口层
 */
@Repository
public interface ScoreScaleMapper extends BaseMapper<ScoreScale> {

    ScoreScale queryByKey(ScoreScale scoreScale);
}
