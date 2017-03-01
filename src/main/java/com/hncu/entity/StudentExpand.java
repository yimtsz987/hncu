package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.io.Serializable;

/**
 * 学生信息扩展表实体类
 */
public class StudentExpand implements Serializable {

    private static final long serialVersionUID = -8060589331610319092L;

    private String sId;
    private String node;
    private Department department;
    private Major major;
    private Classes classes;
    private String titleId;
    private String titleName;
    private String teacherId;
    private String year;
    private String grade;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
    private String step5;
    private String step6;
    private String step7;
    private String step8;
    private String step9;
    private Integer stepNow;
    private String scheduleNum;
    private String answerId;
    private String answerFlag;
    private String expandFlag;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public String getStep4() {
        return step4;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public String getStep5() {
        return step5;
    }

    public void setStep5(String step5) {
        this.step5 = step5;
    }

    public String getStep6() {
        return step6;
    }

    public void setStep6(String step6) {
        this.step6 = step6;
    }

    public String getStep7() {
        return step7;
    }

    public void setStep7(String step7) {
        this.step7 = step7;
    }

    public String getStep8() {
        return step8;
    }

    public void setStep8(String step8) {
        this.step8 = step8;
    }

    public String getStep9() {
        return step9;
    }

    public void setStep9(String step9) {
        this.step9 = step9;
    }

    public Integer getStepNow() {
        return stepNow;
    }

    public void setStepNow(Integer stepNow) {
        this.stepNow = stepNow;
    }

    public String getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(String scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(String answerFlag) {
        this.answerFlag = answerFlag;
    }

    public String getExpandFlag() {
        return expandFlag;
    }

    public void setExpandFlag(String expandFlag) {
        this.expandFlag = expandFlag;
    }
}
