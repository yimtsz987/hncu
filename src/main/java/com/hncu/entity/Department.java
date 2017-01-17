package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 院系信息实体类
 */
public class Department extends BaseEntity {

    private static final long serialVersionUID = 5863032773712246828L;

    private String dNo;
    private String name;
    private String count;
    private String sort;

    public Department(){

    }

    public Department(String id){
        super();
        this.id = id;
    }

    public String getdNo() {
        return dNo;
    }

    public void setdNo(String dNo) {
        this.dNo = dNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
