package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 系统参数实体类
 */
public class SysParam extends BaseEntity {

    private String paramKey;
    private String label;
    private String paramValue;

    public SysParam(){

    }

    public SysParam(String id){
        super();
        this.id = id;
    }

    public SysParam(String id,String paramKey){
        super();
        this.id = id;
        this.paramKey = paramKey;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
