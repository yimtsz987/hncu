package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
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
    private String oldScore;
    private String year;
    private String createById;
    private String teacherComment;
    private String markingScore;
    private String reviewScore;
    private String answerScore;
    private String scoreMD5;
    private String checkFlag;

    public void preInsertSchoolReport(){
        Date now = Calendar.getInstance().getTime();
        this.setDateTime(now);
        this.setCreateById(UserUtils.getCurrentUser().getId());
    }

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

    public String getOldScore() {
        return oldScore;
    }

    public void setOldScore(String oldScore) {
        this.oldScore = oldScore;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public String getMarkingScore() {
        return markingScore;
    }

    public void setMarkingScore(String markingScore) {
        this.markingScore = markingScore;
    }

    public String getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(String answerScore) {
        this.answerScore = answerScore;
    }

    public String getScoreMD5() {
        return scoreMD5;
    }

    public void setScoreMD5(String scoreMD5) {
        this.scoreMD5 = scoreMD5;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }
}
