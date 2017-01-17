package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * Created by yimts on 2016-12-26.
 */
public class Permission extends BaseEntity {

    private static final long serialVersionUID = -1689140650020494254L;

    private String permission;
    private Role role;
    private User user;

    public Permission(){

    }

    public Permission(User user){
        this();
        this.user = user;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
