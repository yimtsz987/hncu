package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 开题实体类
 */
public class OpenTitle extends BaseEntity {

    private static final long serialVersionUID = 7157650089239814190L;
    private String studentId;
    private Date uploadDate;
    private String year;
    private String titleId;
    private String reportFlag;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;
    private List<Schedule> scheduleList;
    private String checkStr;
    private String teacherId;
    private String teacherAdvise;

    private String step;

    public OpenTitle(){
        super();
    }

    public OpenTitle(List<Schedule> scheduleList){
        super();
        this.scheduleList = scheduleList;
    }

    /**
     * 上传之前调用
     */
    public void preInsertOpenTitleReport(){
        Date now = Calendar.getInstance().getTime();
        this.setUploadDate(now);
        this.setStudentId(UserUtils.getCurrentUser().getId());
        this.setTitleId(UserUtils.getCurrentUser().getStudent().getTitleId());
        this.setTeacherId(UserUtils.getCurrentUser().getStudent().getTeacherId());
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
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

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
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

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
