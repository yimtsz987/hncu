package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专业信息数据持久化接口
 */
@Repository
public interface MajorMapper extends BaseMapper<Major> {

    List<String> queryMajorNameList();
}
