package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 专业信息实体类
 */
public class Major extends BaseEntity {

    private static final long serialVersionUID = -4545028206456407299L;

    private String name;
    private String sort;
    private Department department;

    public Major(){

    }

    public Major(String id){
        super();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
