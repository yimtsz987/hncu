package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Classes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 班级信息数据持久化操作信息类
 */
@Repository
public interface ClassesMapper extends BaseMapper<Classes> {

    /**
     * 通过班级号查询班级信息
     * @param classes
     * @return
     */
    Classes queryByClasses(@Param("classes") String classes);
}
