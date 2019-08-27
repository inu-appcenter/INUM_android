package org.gowoon.inum.model;

import lombok.Data;

@Data
public class UserData {
    public static UserData userData = new UserData();

    public static UserData getInstance() {
        return userData;
    }

    private String name;
    private String id;
    private String passwd;
    private String phone;
}