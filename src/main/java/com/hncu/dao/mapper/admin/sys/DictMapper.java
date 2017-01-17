package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典信息的持久化操作接口
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {

    List<String> queryTypeList (Dict dict);
}
