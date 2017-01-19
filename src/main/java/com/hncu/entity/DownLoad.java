package com.hncu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hncu.common.BaseEntity;

import java.util.Date;

/**
 * 下载信息实体类
 */
public class DownLoad extends BaseEntity {

    private static final long serialVersionUID = 3540342573974480588L;
    private String title;
    private String issuer;
    private Date issueDate;
    private String description;
    private String receive;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;

    public  DownLoad(){

    }

    public  DownLoad(String id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
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
}
