package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.util.List;

/**
 * 问题实体类
 */
public class TeacherQuestion extends BaseEntity {
    private static final long serialVersionUID = -1436347222400439455L;

    private User user;
    private Marking marking;
    private List<Marking> questionList;
    private String studentId;
    private String teacherId;
    private String questionState;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Marking getMarking() {
        return marking;
    }

    public void setMarking(Marking marking) {
        this.marking = marking;
    }

    public List<Marking> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Marking> questionList) {
        this.questionList = questionList;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getQuestionState() {
        return questionState;
    }

    public void setQuestionState(String questionState) {
        this.questionState = questionState;
    }
}
