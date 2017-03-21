package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师学生实体类
 */
public class TeacherAndStudent extends BaseEntity {

    private static final long serialVersionUID = -4100163731081737588L;
    private String teacherId;
    private String year;
    private Integer studentSum;
    private String studentIds;
    private String reviewTeacherId;

    public TeacherAndStudent(){
        super();
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

    public Integer getStudentSum() {
        return studentSum;
    }

    public void setStudentSum(Integer studentSum) {
        this.studentSum = studentSum;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getReviewTeacherId() {
        return reviewTeacherId;
    }

    public void setReviewTeacherId(String reviewTeacherId) {
        this.reviewTeacherId = reviewTeacherId;
    }
}
