package org.gowoon.inum.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("message")
    public String message;

    @SerializedName("token")
    public String token;

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("tel")
    public String tel;

    @SerializedName("letter")
    public Integer letter;

    @SerializedName("product")
    public Integer product;

    public LoginResult(String message, String token , String id, String name , String tel) {
        this.message = message;
        this.token = token;
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
