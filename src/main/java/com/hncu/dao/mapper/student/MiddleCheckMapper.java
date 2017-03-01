package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.MiddleCheck;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 中期检查数据持久化操作接口层
 */
@Repository
public interface MiddleCheckMapper extends BaseMapper<MiddleCheck> {

    /**
     * 上传中期检查材料
     * @param middleCheck
     * @return
     */
    int uploadMiddleCheck(MiddleCheck middleCheck);

    /**
     * 步骤七完成
     * @param user
     * @return
     */
    int stepSevenEnd(User user);

    /**
     * 根据学生ID和中期材料参数ID查询中期检查
     * @param studentId
     * @param paramId
     * @return
     */
    MiddleCheck queryMiddleCheckByParamId(@Param("studentId") String studentId, @Param("paramId") String paramId);

    /**
     * 更新中期检查材料
     * @param middleCheck
     * @return
     */
    int uploadMiddleCheckUpdate(MiddleCheck middleCheck);
}
