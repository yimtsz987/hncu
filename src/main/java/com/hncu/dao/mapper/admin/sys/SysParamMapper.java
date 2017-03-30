package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.SysParam;
import com.hncu.entity.TeacherYearInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统参数接口层
 */
@Repository
public interface SysParamMapper extends BaseMapper<SysParam> {

    /**
     * 通过键名查询
     * @param sysParam
     * @return
     */
    SysParam queryParamByKey (String paramKey);

    List<String> queryKeyList (SysParam sysParam);

    List<String> queryLabelList (SysParam sysParam);

    int insertTeacherYearAnswer(List<TeacherYearInfo> teacherYearInfoList);

    int insertTeacherYearReview(List<TeacherYearInfo> teacherYearInfoList);

    int insertTeacherYearStudent(List<TeacherYearInfo> teacherYearInfoList);

    List<TeacherYearInfo> queryTeacherYearInfo(@Param("teacherId") String teacherId);
}
