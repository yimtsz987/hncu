package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseEntity;
import com.hncu.common.BaseMapper;
import com.hncu.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师管理数据持久化接口层
 */
@Repository
public interface TeacherMapper extends BaseMapper<TeacherInfo> {

    /**
     * 插入教师基本信息
     * @param teacherInfo
     * @return
     */
    int insertUserInfo (TeacherInfo teacherInfo);

    /**
     * 插入教师拓展信息
     * @param teacherInfo
     * @return
     */
    int insertExpandInfo (TeacherInfo teacherInfo);

    /**
     * 插入教师角色信息
     * @param userId
     * @return
     */
    int insertTeacherRole(@Param("userId") String userId);

    /**
     * 更新教师基本信息
     * @param teacherInfo
     * @return
     */
    int updateUserInfo(TeacherInfo teacherInfo);

    /**
     * 更新教师拓展信息
     * @param teacherInfo
     * @return
     */
    int updateExpandInfo(TeacherInfo teacherInfo);

    /**
     * 删除教师基本信息
     * @param teacherInfo
     * @return
     */
    int deleteUserInfo(TeacherInfo teacherInfo);

    /**
     * 删除教师拓展信息
     * @param teacherInfo
     * @return
     */
    int deleteExpandInfo(TeacherInfo teacherInfo);

    /**
     * 删除教师角色信息
     * @param teacherInfo
     * @return
     */
    int deleteTeacherRole(TeacherInfo teacherInfo);
}
