package com.hncu.entity;

import java.io.Serializable;

/**
 * 教师信息扩展实体类
 */
public class TeacherExpand implements Serializable {

    private static final long serialVersionUID = -2944170413546722593L;

    private String tId;
    private String node;
    private Department department;
    private String researchDirection;
    private String professionalTitle;
    private String expand_flag;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getResearch_direction() {
        return researchDirection;
    }

    public void setResearch_direction(String research_direction) {
        this.researchDirection = research_direction;
    }

    public String getProfessional_title() {
        return professionalTitle;
    }

    public void setProfessional_title(String professional_title) {
        this.professionalTitle = professional_title;
    }

    public String getExpand_flag() {
        return expand_flag;
    }

    public void setExpand_flag(String expand_flag) {
        this.expand_flag = expand_flag;
    }
}
