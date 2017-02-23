package com.hncu.dao.mapper.student;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Title;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 选择课题数据持久化操作接口层
 */
@Repository
public interface ChooseTitleMapper extends BaseMapper<Title> {

    /**
     * 选择课题
     * @param title
     * @return
     */
    int chooseTitle(Title title);

    /**
     * 完成步骤二
     * @param user
     * @return
     */
    int stepTwoEnd(User user);

    /**
     * 学生选择课题编辑
     * @param titleId
     * @param sId
     * @return
     */
    int titleIdEdit(@Param("titleId") String titleId, @Param("sId") String sId);
}
