package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息实体类
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = 3715918456503317014L;

    private String name;
    private String description;
    private String count;
    private String dataScope;
    private String roleName;
    private User user;
    private String permission;
    private List<Permission> permissionList;
    private List<User> userList;

    public Role(){

    }

    public Role(String id) {
        this.id = id;
    }

    public Role(User user,List<Permission> permissionList) {
        this();
        this.user = user;
        this.permissionList = permissionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<String> getPermissionsName(){
        List<String> list = new ArrayList<String>();
        List<Permission> permissionList = getPermissionList();
        for (Permission permission : permissionList){
            list.add(permission.getPermission());
        }
        return list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
