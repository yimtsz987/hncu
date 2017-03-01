package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 中期检查实体类
 */
public class MiddleCheck extends BaseEntity{
    private static final long serialVersionUID = 7075240901764235314L;

    private String studentId;
    private Date uploadDate;
    private String year;
    private String titleId;
    private String state;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;
    private String paramId;

    /**
     * 上传中期材料之前调用
     */
    public void preInsertMiddleCheck(){
        Date now = Calendar.getInstance().getTime();
        this.setUploadDate(now);
        this.setStudentId(UserUtils.getCurrentUser().getId());
        this.setYear(SysParamUtil.getParamValue("year"));
        this.setTitleId(UserUtils.getCurrentUser().getStudent().getTitleId());
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }
}
