package com.hncu.entity;

import com.google.common.collect.Lists;
import com.hncu.common.BaseEntity;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.DateUtils;
import com.hncu.utils.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 答辩组
 */
public class Answer extends BaseEntity {

    private static final long serialVersionUID = -131326098988456114L;
    private String leaderId;
    private String year;
    private String studentIds;
    private String teacherIds;
    private String answerClasses;
    private String address;
    private Date answerTime;
    private Integer teacherNum;

    private String dateString;

    private String TeacherId;

    private String teacherNameString;

    private Integer countDown;

    private List<User> studentList = Lists.newArrayList(); //答辩组学生集合
    private List<User> teacherList = Lists.newArrayList(); //答辩组老师集合

    public Answer(){
        super();
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public List<User> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<User> studentList) {
        this.studentList = studentList;
    }

    public List<User> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<User> teacherList) {
        this.teacherList = teacherList;
    }

    public String getAnswerClasses() {
        return answerClasses;
    }

    public void setAnswerClasses(String answerClasses) {
        this.answerClasses = answerClasses;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherNameString() {
        return teacherNameString;
    }

    public void setTeacherNameString(String teacherNameString) {
        this.teacherNameString = teacherNameString;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    /*public List<String> getStudentIdList (){
        List<String> studentIdList = Lists.newArrayList();
        for (User user : studentList){
            studentIdList.add(user.getId());
        }
        return studentIdList;
    }

    public void setStudentIdList(List<String> studentIds){
        studentList = Lists.newArrayList();
        for (String studentId : studentIds){
            User user = new User(studentId);
            studentList.add(user);
        }
    }

    public String getAnswerStudentIds(){
        return CollectionUtil.extractToString(studentList, "id", ",");
    }

    public void setAnswerStudentIds (String studentIds){
        if (StringUtils.isNotBlank(studentIds)){
            String[] ids = StringUtils.split(studentIds, ",");
            setStudentIdList(Lists.<String>newArrayList(ids));
        }
    }

    public List<String> getTeacherIdList (){
        List<String> teacherIdList = Lists.newArrayList();
        for (User user : teacherList){
            teacherIdList.add(user.getId());
        }
        return teacherIdList;
    }

    public void setTeacherIdList(List<String> teacherIds){
        teacherList = Lists.newArrayList();
        for (String teacherId : teacherIds){
            User user = new User(teacherId);
            teacherList.add(user);
        }
    }

    public String getAnswerTeacherIds(){
        return CollectionUtil.extractToString(teacherList, "id", ",");
    }

    public void setAnswerTeacherIds (String teacherIds){
        if (StringUtils.isNotBlank(teacherIds)){
            String[] ids = StringUtils.split(teacherIds, ",");
            setTeacherIdList(Lists.<String>newArrayList(ids));
        }
    }*/

}
