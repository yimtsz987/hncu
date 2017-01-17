package com.hncu.service.admin.sys;

import com.hncu.common.BaseEntity;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.SysParamMapper;
import com.hncu.entity.SysParam;
import com.hncu.utils.CacheUtil;
import com.hncu.utils.SysParamUtil;
import com.hncu.web.admin.sys.SysParamController;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统参数服务层
 */
@Service
public class SysParamService extends BaseService<SysParamMapper, SysParam>{

    public SysParam queryParamByKey (String paramKey){
        return mapper.queryParamByKey(paramKey);
    }

    public List<String> queryKeyList(SysParam sysParam){
        return mapper.queryKeyList(sysParam);
    }

    public List<String> queryLabelList(SysParam sysParam){
        return mapper.queryLabelList(sysParam);
    }

    @Override
    public boolean save(SysParam entity) {

        CacheUtil.put(SysParamUtil.SYS_PARAM_CACHE_PREFIX + entity.getParamKey(), entity);
        return super.save(entity);
    }
}
