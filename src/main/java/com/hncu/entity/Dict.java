package com.hncu.entity;

import com.hncu.common.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 字典实体类
 */
public class Dict extends BaseEntity {
    @NotEmpty(message = "键值不能为空")
    @Length(max = 10, message = "键值不能超过10个字符")
    private String value;	// 数据值
    @NotEmpty (message = "标签名不能为空")
    private String label;	// 标签名
    @NotEmpty (message = "类型不能为空")
    private String type;	// 类型
    @NotEmpty (message = "描述不能为空")
    private String description;// 描述
    @NotNull(message = "排序不能为空")
    private Integer sort;	// 排序
    private String parentId;//父Id

    public Dict() {
    }

    public Dict(String id) {
        super(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
