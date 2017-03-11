package com.hncu.entity;

import com.hncu.common.BaseEntity;

/**
 * 学生信息实体类
 */
public class StudentInfo extends BaseEntity {


    private static final long serialVersionUID = 1871908920910292022L;
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
    private String sId;
    private String node;
    private Department department;
    private Major major;
    private Classes classes;
    private Title title;
    private TeacherInfo teacher;
    private String year;
    private String grade;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
    private String step5;
    private String step6;
    private String step7;
    private String step8;
    private String step9;
    private Integer stepNow;
    private String answerId;
    private String answerFlag = ANSWER_FLAG_NO;
    private String expandFlag = EXPAND_FLAG_STUDENT;
    private SchoolReport schoolReport;

    public StudentInfo(){

    }

    public StudentInfo(String id){
        super(id);
    }

    public void preInsertStudent(){
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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public TeacherInfo getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherInfo teacher) {
        this.teacher = teacher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public String getStep4() {
        return step4;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public String getStep5() {
        return step5;
    }

    public void setStep5(String step5) {
        this.step5 = step5;
    }

    public String getStep6() {
        return step6;
    }

    public void setStep6(String step6) {
        this.step6 = step6;
    }

    public String getStep7() {
        return step7;
    }

    public void setStep7(String step7) {
        this.step7 = step7;
    }

    public String getStep8() {
        return step8;
    }

    public void setStep8(String step8) {
        this.step8 = step8;
    }

    public String getStep9() {
        return step9;
    }

    public void setStep9(String step9) {
        this.step9 = step9;
    }

    public Integer getStepNow() {
        return stepNow;
    }

    public void setStepNow(Integer stepNow) {
        this.stepNow = stepNow;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(String answerFlag) {
        this.answerFlag = answerFlag;
    }

    public String getExpandFlag() {
        return expandFlag;
    }

    public void setExpandFlag(String expandFlag) {
        this.expandFlag = expandFlag;
    }

    public SchoolReport getSchoolReport() {
        return schoolReport;
    }

    public void setSchoolReport(SchoolReport schoolReport) {
        this.schoolReport = schoolReport;
    }
}
