package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.SysParam;
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
}
