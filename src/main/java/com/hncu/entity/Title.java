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
    private String teacherId;
    private String year;
    private String selectFlag;
    private String studentName;

    private String checkTitle;

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

    public String getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(String selectFlag) {
        this.selectFlag = selectFlag;
    }

    public String getCheckTitle() {
        return checkTitle;
    }

    public void setCheckTitle(String checkTitle) {
        this.checkTitle = checkTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
