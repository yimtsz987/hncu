package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 *  超链接实体类
 */
public class Link extends BaseEntity {

    private static final long serialVersionUID = -1120434208356512029L;

    private String title;
    private String url;
    private String sort;
    private String icon;
    private String target;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
