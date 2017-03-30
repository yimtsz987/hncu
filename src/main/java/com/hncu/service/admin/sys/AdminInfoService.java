package com.hncu.service.admin.sys;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.UserMapper;
import com.hncu.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员用户信息服务层
 */
@Service
public class AdminInfoService extends BaseService<UserMapper, User>{

    @Transactional(readOnly = false)
    public void updateAdminInfo(User user){
        user.preUpdate();
        mapper.update(user);
    }
}
