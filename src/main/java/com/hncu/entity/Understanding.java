package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 审题报告实体类
 */
public class Understanding extends BaseEntity {

    private static final long serialVersionUID = 144199328539036066L;
    private String studentId;
    private Date uploadDate;
    private String year;
    private String titleId;
    private String passFlag;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;
    private String checkStr;
    private String teacherId;
    private String teacherAdvise;


    /**
     * 上传之前调用
     */
    public void preInsert(){
        Date now = Calendar.getInstance().getTime();
        this.setUploadDate(now);
        this.setStudentId(UserUtils.getCurrentUser().getId());
        this.setYear(SysParamUtil.getParamValue("year"));
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

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
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
}
