package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 通知用户实体类
 */
public class NoticeUser extends BaseEntity{

    private static final long serialVersionUID = 7702739517989343053L;

    private String noticeId;
    private String userId;
    private String isRead;

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
}
