package org.gowoon.inum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfoVO implements Serializable {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("major")
    @Expose
    public String major;

    @SerializedName("tel")
    @Expose
    public String tel;
}
