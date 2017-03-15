package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师端 - 中期检查实体类
 */
public class TeacherMiddleCheck extends BaseEntity{

    private static final long serialVersionUID = -2097518524165116165L;
    private User user;
    private MiddleCheck middleCheck;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MiddleCheck getMiddleCheck() {
        return middleCheck;
    }

    public void setMiddleCheck(MiddleCheck middleCheck) {
        this.middleCheck = middleCheck;
    }
}
