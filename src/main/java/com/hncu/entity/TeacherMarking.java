package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师端 - 批阅实体类
 */
public class TeacherMarking extends BaseEntity{

    private static final long serialVersionUID = 353967354392287945L;
    private User user;
    private Marking marking;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Marking getMarking() {
        return marking;
    }

    public void setMarking(Marking marking) {
        this.marking = marking;
    }
}
