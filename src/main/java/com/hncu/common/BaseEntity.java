package com.hncu.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hncu.entity.User;
import com.hncu.utils.UserUtils;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 所有实体类的超类
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 6793508664199867228L;

    /**
     * 删除标记 - 正常
     */
    public static final String DEL_FLAG_NORMAL = "0";
    /**
     * 删除标记 - 删除
     */
    public static final String DEL_FLAG_DELETE = "1";
    /**
     * 验证标记 - 已验证
     */
    public static final String VALIDATE_YES = "1";
    /**
     * 验证标记 - 未验证
     */
    public static final String VALIDATE_NO = "0";
    /**
     * 拓展标记 - 管理员expandFlag
     */
    public static final String EXPAND_FLAG_ADMIN = "0";
    /**
     * 拓展标记 - 学生
     */
    public static final String EXPAND_FLAG_STUDENT = "1";
    /**
     * 拓展标记 - 教师
     */
    public static final String EXPAND_FLAG_TEACHER = "2";
    /**
     * 分组标记 - 未分组
     */
    public static final String ANSWER_FLAG_NO = "0";
    /**
     * 分组标记 - 已分组
     */
    public static final String ANSWER_FLAG_YES = "1";

    protected String id;  //主键
    protected User createBy; //创建者
    protected User updateBy; //更新者
    protected Date createDate; //创建时间
    protected Date updateDate; //更新时间
    protected String delFlag = DEL_FLAG_NORMAL; //删除标记  （0正常 1删除）
    @Length(max = 100, message = "备注信息不能超过100个字符")
    protected String remarks;

    /**
     * 执行新增操作之前需要执行的操作
     * 在service类里，调用mapper接口的inset方法以前主动调用
     */
    public void preInsert () {
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            this.createBy = user;
            this.updateBy = user;
        }

        Date now = Calendar.getInstance().getTime();
        this.setCreateDate(now);
        this.setUpdateDate(now);
    }

    public void preUpdate () {
        User user = UserUtils.getCurrentUser();
        if (user != null){
            this.updateBy = user;
        }
        Date now = Calendar.getInstance().getTime();
        this.setUpdateDate(now);
    }

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
