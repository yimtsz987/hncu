package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师端 - 审题实体类
 */
public class TeacherUnderstanding extends BaseEntity{

    private static final long serialVersionUID = 533774088229727152L;
    private User user;
    private Understanding understanding;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Understanding getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(Understanding understanding) {
        this.understanding = understanding;
    }
}
