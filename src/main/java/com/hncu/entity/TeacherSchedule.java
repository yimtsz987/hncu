package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.util.List;

/**
 * 教师端 - 分期报告实体类
 */
public class TeacherSchedule extends BaseEntity{

    private static final long serialVersionUID = -6766230007981576818L;
    private User user;
    private Schedule schedule;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
