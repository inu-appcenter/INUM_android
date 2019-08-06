package org.gowoon.inum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Declare {
    public static Declare declare = new Declare();
    public static Declare getInstance(){return declare;}


    private String kind;
    private String senderId;
    private String productId;


    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSenderId(){
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getProductId() { return this.productId;}

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
