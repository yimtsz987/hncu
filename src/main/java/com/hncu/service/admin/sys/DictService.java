package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.DictMapper;
import com.hncu.entity.Dict;
import com.hncu.utils.CacheUtil;
import com.hncu.utils.DictUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典管理服务层
 */
@Service
public class DictService extends BaseService<DictMapper, Dict> {

    public List<String> queryTypeList(Dict dict){
        return mapper.queryTypeList(dict);
    }

    @Override
    public boolean delete(Dict entity) {
        boolean res = super.delete(entity);
        CacheUtil.remove(DictUtils.DICT_CACHE_PREFIX + entity.getType());
        return res;
    }

    @Override
    public boolean save(Dict entity) {
        boolean res = super.save(entity);
        CacheUtil.remove(DictUtils.DICT_CACHE_PREFIX + entity.getType());
        return res;
    }
}
