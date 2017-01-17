package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户信息数据持久化操作接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    User queryUserByUsername(User user);
}
