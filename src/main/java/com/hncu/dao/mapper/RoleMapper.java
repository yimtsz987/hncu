package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Permission;
import com.hncu.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色信息数据持久化操作接口
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户id查询角色信息
     * @param role
     * @return
     */
    List<Role> queryByUserId (Role role);

    List<Permission> queryPermissionByUserId(Permission permission);
}
