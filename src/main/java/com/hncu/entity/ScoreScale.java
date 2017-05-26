package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 成绩比例实体类
 */
public class ScoreScale extends BaseEntity {
    private static final long serialVersionUID = -1106949215283177903L;

    private String name;
    private Integer scale;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
