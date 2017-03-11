package com.hncu.dao.mapper.teacher;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Title;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师端 - 课题数据持久化操作接口层
 */
@Repository
public interface TitleMapper extends BaseMapper<Title>{

    /**
     * 新增课题
     * @param title
     * @return
     */
    int insertTitle(Title title);

    /**
     * 更新课题
     * @param title
     * @return
     */
    int updateTitle(Title title);

    /**
     * 删除课题
     * @param id
     * @param teacherId
     * @return
     */
    int deleteTitle(@Param("id") String id, @Param("teacherId") String teacherId);
}
