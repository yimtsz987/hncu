package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.sys.MajorMapper;
import com.hncu.entity.Major;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专业菜单信息服务层
 */
@Service
public class MajorService extends BaseService<MajorMapper, Major> {

    public List<String> queryMajorNameList(){
        return mapper.queryMajorNameList();
    }
}
