package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 教师信息实体类
 */
public class TeacherInfo extends BaseEntity {

    private static final long serialVersionUID = 6756443887658510024L;
    private String name;
    private String username;
    private String password = "3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe";
    private String idcard;
    private String mobile;
    private String address;
    private String sex;
    private String age;
    private String email;
    private String is_validate = VALIDATE_NO;
    private String qq;
    private String headerPic;
    private String tId;
    private String node;
    private Department department;
    private String researchDirection;
    private String professionalTitle;
    private String expandFlag = EXPAND_FLAG_TEACHER;
    private String answerFlag;
    private String reviewFlag;
    private String answerId;
    private String isLeader;

    //choose
    private String year;
    private Integer studentSum;
    private String studentIds;

    private String sysAdvice;
    private Integer classesStudentNumber;  //指导该班级学生数量
    private String queryClasses;

    public TeacherInfo(){

    }

    public TeacherInfo(String id){
        super(id);
    }

    public void preInsertTeacher(){
        if ("0".equals(sex)){
            headerPic = "img/man.jpg";
        } else {
            headerPic = "img/woman.jpg";
        }
        username = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_validate() {
        return is_validate;
    }

    public void setIs_validate(String is_validate) {
        this.is_validate = is_validate;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

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

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getExpandFlag() {
        return expandFlag;
    }

    public void setExpandFlag(String expandFlag) {
        this.expandFlag = expandFlag;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getStudentSum() {
        return studentSum;
    }

    public void setStudentSum(Integer studentSum) {
        this.studentSum = studentSum;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(String answerFlag) {
        this.answerFlag = answerFlag;
    }

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getSysAdvice() {
        return sysAdvice;
    }

    public void setSysAdvice(String sysAdvice) {
        this.sysAdvice = sysAdvice;
    }

    public Integer getClassesStudentNumber() {
        return classesStudentNumber;
    }

    public void setClassesStudentNumber(Integer classesStudentNumber) {
        this.classesStudentNumber = classesStudentNumber;
    }

    public String getQueryClasses() {
        return queryClasses;
    }

    public void setQueryClasses(String queryClasses) {
        this.queryClasses = queryClasses;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }
}
