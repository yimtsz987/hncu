package com.hncu.entity;

import com.google.common.collect.Lists;
import com.hncu.common.BaseEntity;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 上传实体类
 */
public class Upload extends BaseEntity{
    private static final long serialVersionUID = -3354995741505168464L;

    private String title;
    private User issuer;
    private Date issueDate;
    private String description;
    private String receive;
    private String uploadFile;
    private String uploadPath;
    private String uploadFileOldName;
    private List<Role> roleList = Lists.newArrayList();


    /**
     * 上传之前调用
     */
    public void preInsert(){
        Date now = Calendar.getInstance().getTime();
        User is = new User(UserUtils.getCurrentUser().getId());
        this.setIssuer(is);
        this.setIssueDate(now);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }

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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setUploadFileOldName(String uploadFileOldName) {
        this.uploadFileOldName = uploadFileOldName;
    }

    public List<String> getRoleIdList(){
        List<String> roleIdList = Lists.newArrayList();
        for (Role role : roleList){
            roleIdList.add(role.getId());
        }
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        roleList = Lists.newArrayList();
        for (String roleId : roleIdList) {
            if (StringUtils.isNotBlank(roleId)) {
                Role role = new Role(roleId);
                roleList.add(role);
            }
        }
    }

    public String getRoleIds (){
        return CollectionUtil.extractToString(roleList, "id", ",");
    }
}
