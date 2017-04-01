package com.hncu.service;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.UserMapper;
import com.hncu.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户信息服务层
 */
@Service
public class InfoService extends BaseService<UserMapper, User> {

    @Transactional(readOnly = false)
    public void updateAdminInfo(User user){
        user.preUpdate();
        mapper.update(user);
    }

    @Transactional(readOnly = false)
    public void updateTeacherInfo(User user){
        user.preUpdate();
        mapper.update(user);
        mapper.updateTeacherExpandInfo(user);
    }

    @Transactional(readOnly = false)
    public void updateStudentInfo(User user){
        user.preUpdate();
        mapper.update(user);
        mapper.updateStudentExpandInfo(user);
    }
}
