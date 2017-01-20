package com.hncu.entity;

/**
 * 上传返回参数实体类
 */
public class UploadParam {

    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;

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
