package com.hncu.entity;

import com.hncu.common.BaseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户信息实体类
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = -2219202496885416841L;

    private String name;
    private String username;
    private String password;
    private String idcard;
    private String mobile;
    private String address;
    private String sex;
    private String age;
    private String email;
    private String is_validate;
    private String qq;
    private String expandFlag;
    private StudentExpand student;
    private TeacherExpand teacher;
    private String headerPic;
    private List<Role> roleList;//一个用户具有多个角色

    private String sysYearParamId;
    private String sysParamYear;

    public User(){
        super();
    }

    public User(String id){
        super(id);
    }

    public User(String id,String username){
        super();
        this.id = id;
        this.username = username;
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

    public String getExpandFlag() {
        return expandFlag;
    }

    public void setExpandFlag(String expandFlag) {
        this.expandFlag = expandFlag;
    }

    public StudentExpand getStudent() {
        return student;
    }

    public void setStudent(StudentExpand student) {
        this.student = student;
    }

    public TeacherExpand getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherExpand teacher) {
        this.teacher = teacher;
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Set<String> getRolesName(){
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles){
            set.add(role.getRoleName());
        }
        return set;
    }

    public String getSysYearParamId() {
        return sysYearParamId;
    }

    public void setSysYearParamId(String sysYearParamId) {
        this.sysYearParamId = sysYearParamId;
    }

    public String getSysParamYear() {
        return sysParamYear;
    }

    public void setSysParamYear(String sysParamYear) {
        this.sysParamYear = sysParamYear;
    }
}
