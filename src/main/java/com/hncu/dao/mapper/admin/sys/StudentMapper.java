package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.StudentInfo;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生管理数据持久化操作接口层
 */
@Repository
public interface StudentMapper extends BaseMapper<StudentInfo> {

    /**
     * 插入学生基本信息
     * @param studentInfo
     * @return
     */
    int insertUserInfo (StudentInfo studentInfo);

    /**
     * 插入学生拓展信息
     * @param studentInfo
     * @return
     */
    int insertExpandInfo (StudentInfo studentInfo);

    /**
     * 插入学生角色信息
     * @param userId
     * @return
     */
    int insertStudentRole(@Param("userId") String userId);

    /**
     * 更新学生基本信息
     * @param studentInfo
     * @return
     */
    int updateUserInfo(StudentInfo studentInfo);

    /**
     * 更新学生拓展信息
     * @param studentInfo
     * @return
     */
    int updateExpandInfo(StudentInfo studentInfo);

    /**
     * 删除学生基本信息
     * @param studentInfo
     * @return
     */
    int deleteUserInfo(StudentInfo studentInfo);

    /**
     * 删除学生拓展信息
     * @param studentInfo
     * @return
     */
    int deleteExpandInfo(StudentInfo studentInfo);

    /**
     * 删除学生角色信息
     * @param studentInfo
     * @return
     */
    int deleteStudentRole(StudentInfo studentInfo);

    /**
     * 通过班级查询学生信息
     * @param studentInfo
     * @return
     */
    List<StudentInfo> queryStudentListByClasses(StudentInfo studentInfo);
}
