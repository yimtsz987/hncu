package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 中期检查参数实体类
 */
public class MiddleCheckParam extends BaseEntity{

    private static final long serialVersionUID = 4224474616667722825L;

    private String name;
    private String description;
    private String studentState;
    private String suffix;
    private String middleCheckReportId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentState() {
        return studentState;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getMiddleCheckReportId() {
        return middleCheckReportId;
    }

    public void setMiddleCheckReportId(String middleCheckReportId) {
        this.middleCheckReportId = middleCheckReportId;
    }
}
