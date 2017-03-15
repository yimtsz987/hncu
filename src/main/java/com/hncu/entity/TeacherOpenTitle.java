package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师端 - 开题实体类
 */
public class TeacherOpenTitle extends BaseEntity{

    private static final long serialVersionUID = 2484137213359005920L;
    private User user;
    private OpenTitle openTitle;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OpenTitle getOpenTitle() {
        return openTitle;
    }

    public void setOpenTitle(OpenTitle openTitle) {
        this.openTitle = openTitle;
    }
}
