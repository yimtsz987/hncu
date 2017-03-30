package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师年份信息实体类
 */
public class TeacherYearInfo extends BaseEntity {
    private static final long serialVersionUID = -5459778348692573239L;

    private String teacherId;
    private String year;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
