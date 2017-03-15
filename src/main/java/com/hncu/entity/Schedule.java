package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.UserUtils;

import java.util.Date;

/**
 * 进度计划表实体类
 */
public class Schedule extends BaseEntity {
    private static final long serialVersionUID = 5116478486224547496L;

    private String studentId;
    private String sort;
    private String content;
    private Date startDate;
    private Date endDate;
    private String reportId;
    private String reportFlag;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;
    private long remainingDate;
    private String checkStr;
    private String teacherId;
    private String teacherAdvise;
    private String lastSort;

    public Schedule(){

    }

    public Schedule(String id){
        super();
        this.id = id;
    }

    /**
     * 上传之前调用
     */
    public void preInsertReport(){
        this.setStudentId(UserUtils.getCurrentUser().getId());
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUploadFileOldName() {
        return uploadFileOldName;
    }

    public void setUploadFileOldName(String uploadFileOldName) {
        this.uploadFileOldName = uploadFileOldName;
    }

    public long getRemainingDate() {
        return remainingDate;
    }

    public void setRemainingDate(long remainingDate) {
        this.remainingDate = remainingDate;
    }

    public String getCheckStr() {
        return checkStr;
    }

    public void setCheckStr(String checkStr) {
        this.checkStr = checkStr;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherAdvise() {
        return teacherAdvise;
    }

    public void setTeacherAdvise(String teacherAdvise) {
        this.teacherAdvise = teacherAdvise;
    }

    public String getLastSort() {
        return lastSort;
    }

    public void setLastSort(String lastSort) {
        this.lastSort = lastSort;
    }
}
