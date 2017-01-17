package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 课题信息实体类
 */
public class Title extends BaseEntity {

    private static final long serialVersionUID = 4446211906800421372L;

    private String title;
    private String description;
    private String level;
    private String kind;
    private TeacherExpand teacher;
    private String year;
    private String select_flag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public TeacherExpand getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherExpand teacher) {
        this.teacher = teacher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSelect_flag() {
        return select_flag;
    }

    public void setSelect_flag(String select_flag) {
        this.select_flag = select_flag;
    }
}
