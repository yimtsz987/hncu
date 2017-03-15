package com.hncu.entity;

import com.hncu.common.BaseEntity;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 教师批阅
 */
public class Marking extends BaseEntity {

    private static final long serialVersionUID = -1546163830116953646L;
    private String studentId;
    private Date suploadDate;
    private String suploadFile;
    private String suploadPath;
    private String suploadFileOldName;
    private String studentQuestion;
    private String teacherId;
    private String teacherAdvise;
    private Date tuploadDate;
    private String tuploadFile;
    private String tuploadPath;
    private String tuploadFileOldName;
    private Integer sort;
    private String year;
    private String titleId;
    private String state;
    private String teacherCheckStr;
    private String studentCheckStr;

    /**
     * 学生上传之前调用
     */
    public void preInsertMarkingReportStudent(){
        Date now = Calendar.getInstance().getTime();
        this.setSuploadDate(now);
        this.setStudentId(UserUtils.getCurrentUser().getId());
        this.setYear(SysParamUtil.getParamValue("year"));
        this.setTitleId(UserUtils.getCurrentUser().getStudent().getTitleId());
        this.setTeacherId(UserUtils.getCurrentUser().getStudent().getTeacherId());
    }

    /**
     * 教师上传之前调用
     */
    public void preInsertMarkingReportTeacher(){
        Date now = Calendar.getInstance().getTime();
        this.setSuploadDate(now);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getSuploadDate() {
        return suploadDate;
    }

    public void setSuploadDate(Date suploadDate) {
        this.suploadDate = suploadDate;
    }

    public String getSuploadFile() {
        return suploadFile;
    }

    public void setSuploadFile(String suploadFile) {
        this.suploadFile = suploadFile;
    }

    public String getSuploadPath() {
        return suploadPath;
    }

    public void setSuploadPath(String suploadPath) {
        this.suploadPath = suploadPath;
    }

    public String getSuploadFileOldName() {
        return suploadFileOldName;
    }

    public void setSuploadFileOldName(String suploadFileOldName) {
        this.suploadFileOldName = suploadFileOldName;
    }

    public String getStudentQuestion() {
        return studentQuestion;
    }

    public void setStudentQuestion(String studentQuestion) {
        this.studentQuestion = studentQuestion;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherAdvise() {
        return teacherAdvise;
    }

    public void setTeacherAdvise(String teacherAdvise) {
        this.teacherAdvise = teacherAdvise;
    }

    public Date getTuploadDate() {
        return tuploadDate;
    }

    public void setTuploadDate(Date tuploadDate) {
        this.tuploadDate = tuploadDate;
    }

    public String getTuploadFile() {
        return tuploadFile;
    }

    public void setTuploadFile(String tuploadFile) {
        this.tuploadFile = tuploadFile;
    }

    public String getTuploadPath() {
        return tuploadPath;
    }

    public void setTuploadPath(String tuploadPath) {
        this.tuploadPath = tuploadPath;
    }

    public String getTuploadFileOldName() {
        return tuploadFileOldName;
    }

    public void setTuploadFileOldName(String tuploadFileOldName) {
        this.tuploadFileOldName = tuploadFileOldName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTeacherCheckStr() {
        return teacherCheckStr;
    }

    public void setTeacherCheckStr(String teacherCheckStr) {
        this.teacherCheckStr = teacherCheckStr;
    }

    public String getStudentCheckStr() {
        return studentCheckStr;
    }

    public void setStudentCheckStr(String studentCheckStr) {
        this.studentCheckStr = studentCheckStr;
    }
}
