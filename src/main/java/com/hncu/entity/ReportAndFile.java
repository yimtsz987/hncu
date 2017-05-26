package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 报告及文件实体类
 */
public class ReportAndFile extends BaseEntity{
    private static final long serialVersionUID = -5296881036461587620L;

    private String studentId;
    private String teacherId;
    private String year;
    private String sUploadFile;
    private String sUploadPath;
    private String sUploadFileOldName;
    private String tUploadFile;
    private String tUploadPath;
    private String tUploadFileOldName;
    private String step;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getsUploadFile() {
        return sUploadFile;
    }

    public void setsUploadFile(String sUploadFile) {
        this.sUploadFile = sUploadFile;
    }

    public String getsUploadPath() {
        return sUploadPath;
    }

    public void setsUploadPath(String sUploadPath) {
        this.sUploadPath = sUploadPath;
    }

    public String getsUploadFileOldName() {
        return sUploadFileOldName;
    }

    public void setsUploadFileOldName(String sUploadFileOldName) {
        this.sUploadFileOldName = sUploadFileOldName;
    }

    public String gettUploadFile() {
        return tUploadFile;
    }

    public void settUploadFile(String tUploadFile) {
        this.tUploadFile = tUploadFile;
    }

    public String gettUploadPath() {
        return tUploadPath;
    }

    public void settUploadPath(String tUploadPath) {
        this.tUploadPath = tUploadPath;
    }

    public String gettUploadFileOldName() {
        return tUploadFileOldName;
    }

    public void settUploadFileOldName(String tUploadFileOldName) {
        this.tUploadFileOldName = tUploadFileOldName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
