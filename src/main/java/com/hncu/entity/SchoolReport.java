package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.util.Date;

/**
 * 成绩单实体类
 */
public class SchoolReport extends BaseEntity {

    private static final long serialVersionUID = 6678654989229299250L;
    private String reportId;
    private String studentId;
    private String score;
    private String passFlag;
    private Date dateTime;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
