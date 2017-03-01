package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 教师批阅数据持久化操作接口层
 */
@Repository
public interface MarkingMapper extends BaseMapper<Marking> {

    /**
     * 上传批阅报告
     * @param marking
     * @return
     */
    int uploadMarkingReport(Marking marking);

    /**
     * 步骤六完成
     * @param user
     * @return
     */
    int stepSixEnd(User user);

    /**
     * 查询最后一条批阅
     * @param studentId
     * @return
     */
    Marking queryLastMarkingSort(String studentId);
}
