package com.hncu.entity;

import com.google.common.collect.Lists;
import com.hncu.common.BaseEntity;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 通知公告实体类
 */
public class Notice extends BaseEntity{
    private static final long serialVersionUID = -7962450857592805148L;

    public static final String USER_OBJECT_SINGLE = "0";  //用户角色 - 单个用户
    public static final String USER_OBJECT_MULTI = "1";   //用户角色 - 多个用户
    public static final String USER_OBJECT_ALL = "2";     //用户角色 - 全部用户
    public static final String USER_OBJECT_ROLE = "3";    //用户角色 - 用户角色

    public static final String USER_ROLE_DEFAULT = "0";   //用户角色 - 默认
    public static final String USER_ROLE_STUDENT = "1";   //用户角色 - 学生
    public static final String USER_ROLE_TEACHER = "2";   //用户角色 - 教师
    public static final String USER_ROLE_SECRETARY = "3";  //用户角色 - 教务秘书


    private String title;
    private String content;
    private String issueId;
    private Date issueDate;
    private String userObject;
    private String userRole;
    private String userRoles;
    private String noticeId;
    private String userId;
    private String isRead;
    private List<Role> roleList = Lists.newArrayList();

    private String userRoleName;

    public Notice(String id){
        super();
        super.id = id;
    }

    public void preInserNotice(){
        Date now = Calendar.getInstance().getTime();
        this.setIssueId(UserUtils.getCurrentUser().getId());
        this.setIssueDate(now);
    }

    private List<NoticeUser> noticeUserList = Lists.newArrayList();  //用户通知列表

    public Notice(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getUserObject() {
        return userObject;
    }

    public void setUserObject(String userObject) {
        this.userObject = userObject;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public List<NoticeUser> getNoticeUserList() {
        return noticeUserList;
    }

    public void setNoticeUserList(List<NoticeUser> noticeUserList) {
        this.noticeUserList = noticeUserList;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
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
