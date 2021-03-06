package com.hncu.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 所有数据库持久化操作超级类
 */
public interface BaseMapper<T> {

    List<T> queryList(T entity);

    T queryById(String id);

    int insert(T entity);

    int update(T entity);

    int delete(T entity);

    T checkOnly(T entity);

    /**
     * 查询下载信息
     * @param id
     * @param studentId
     * @return
     */
    T queryDownloadByInfo(@Param("id") String id, @Param("studentId") String studentId);

    List<T> queryStudentInfoList(T entity);
}
