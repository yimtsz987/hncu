package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.DepartmentMapper;
import com.hncu.entity.Department;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 院系信息服务层
 */
@Service
public class DepartmentService extends BaseService<DepartmentMapper, Department> {

    public List<String> queryNameList(){
        return mapper.queryNameList();
    }

    public List<String> queryNodeList(){
        return mapper.queryNodeList();
    }
}
