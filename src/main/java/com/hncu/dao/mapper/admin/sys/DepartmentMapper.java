package com.hncu.dao.mapper.admin.sys;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 院系信息数据持久化操作接口
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

    List<String> queryNameList();

    List<String> queryNodeList();
}
