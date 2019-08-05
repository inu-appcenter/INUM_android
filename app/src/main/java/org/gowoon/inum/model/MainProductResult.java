package org.gowoon.inum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MainProductResult {

    @SerializedName("productImg")
    @Expose
    private List<String> productImg = null;

    @SerializedName("productId")
    @Expose
    private String productId;

    @SerializedName("productName")
    @Expose
    private String productName;

    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("productSelled")
    @Expose
    private Boolean productSelled;

    @SerializedName("updateDate")
    @Expose
    private String updateDate;

    @SerializedName("sellerName")
    @Expose
    private String sellerName;

    @SerializedName("sellerPhone")
    @Expose
    private String sellerPhone;

    @SerializedName("fileFolder")
    @Expose
    private String fileFolder;

    @SerializedName("__v")
    @Expose
    private Integer v;

//    public List<String> getProductImg() {
//        return productImg;
//    }
//
//    public void setProductImg(List<String> productImg) {
//        this.productImg = productImg;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public Integer getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(Integer productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public Boolean getProductSelled() {
//        return productSelled;
//    }
//
//    public void setProductSelled(Boolean productSelled) {
//        this.productSelled = productSelled;
//    }
//
//    public String getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(String updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public String getSellerName() {
//        return sellerName;
//    }
//
//    public void setSellerName(String sellerName) {
//        this.sellerName = sellerName;
//    }
//
//    public String getSellerPhone() {
//        return sellerPhone;
//    }
//
//    public void setSellerPhone(String sellerPhone) {
//        this.sellerPhone = sellerPhone;
//    }
//
//    public String getFileFolder() {
//        return getFileFolder();
//    }
//
//    public void setFileFolder(String fileFolder) {
//        this.fileFolder = fileFolder;
//    }
//
//    public Integer getV() {
//        return v;
//    }
//
//    public void setV(Integer v) {
//        this.v = v;
//    }

}