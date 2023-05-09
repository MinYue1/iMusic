package com.music.pojo;
import java.sql.Date;

public class User {
    int id; //id
    String userCode; //用户名
    String userName; //用户姓名
    String userPassword; //用户密码
    Date birthday; //用户生日
    int gender; //用户性别 1女2男
    String phone; //用户手机
    int userRole; //用户角色
    Date regDate; //注册日期

    String userRoleName; //用户对应的角色，多表查询

    public User() {
    }

    public User(int id, String userCode, String userName, String userPassword, Date birthday, int gender, String phone, int userRole, Date regDate, String userRoleName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.userRole = userRole;
        this.regDate = regDate;
        this.userRoleName = userRoleName;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", userRole=" + userRole +
                ", regDate=" + regDate +
                ", userRoleName='" + userRoleName + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
