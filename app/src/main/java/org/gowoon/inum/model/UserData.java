package org.gowoon.inum.model;

public class UserData {
    public static UserData userData = new UserData();

    public static UserData getInstance() {
        return userData;
    }

    private String name;
    private String id;
    private String passwd;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolID() {
        return id;
    }

    public void setSchoolID(String id) {
        this.id = id;
    }

    public String getPw() {
        return passwd;
    }

    public void setPw(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}