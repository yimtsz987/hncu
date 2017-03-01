package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Marking;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 评阅数据持久化操作接口层
 */
@Repository
public interface ReviewMapper extends BaseMapper<Marking> {

    /**
     * 上传批阅报告
     * @param marking
     * @return
     */
    int uploadReviewReport(Marking marking);

    /**
     * 步骤八完成
     * @param user
     * @return
     */
    int stepEightEnd(User user);

    /**
     * 查询最后一条评阅
     * @param studentId
     * @return
     */
    Marking queryLastReviewSort(String studentId);
}
