package com.example.laboratoriosqlite;
public class User {
    private String fullName;
    private String userName;
    private String eMail;
    private String password;
    private int gender;

    public User() {
    }

    public User(String fullName, String userName, String eMail, String password, int gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public int getGender() {
        return gender;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
