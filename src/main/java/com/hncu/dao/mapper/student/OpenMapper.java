package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.OpenTitle;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 开题数据持久化操作接口层
 */
@Repository
public interface OpenMapper extends BaseMapper<OpenTitle> {

    /**
     * 上传开题报告
     * @param openTitle
     * @return
     */
    int uploadOpenReport(OpenTitle openTitle);

    /**
     * 批量插入进度计划
     * @param openTitle
     * @return
     */
    int insertSchedule(OpenTitle openTitle);

    /**
     * 第四步完成
     * @param user
     * @return
     */
    int stepFourEnd(User user);

    /**
     * 更新开题报告
     * @param openTitle
     * @return
     */
    int uploadOpenReportUpdate(OpenTitle openTitle);
}
