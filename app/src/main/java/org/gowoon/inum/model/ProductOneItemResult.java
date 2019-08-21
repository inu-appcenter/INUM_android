package org.gowoon.inum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ProductOneItemResult {

    private static ProductOneItemResult productData = new ProductOneItemResult();

    public static ProductOneItemResult getInstance() {
        return productData;
    }

    @SerializedName("productImg")
    @Expose
    private List<String> productImg;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("productId")
    @Expose
    private String productId;

    @SerializedName("productName")
    @Expose
    private String productName;

    @SerializedName("productState")
    @Expose
    private String productState;

    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;

    @SerializedName("productStar")
    @Expose
    private Integer productStar;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("productInfo")
    @Expose
    private String productInfo;

    @SerializedName("method")
    @Expose
    private String method;

    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("sellerId")
    @Expose
    private String sellerId;

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
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
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
//    public String getProductState() {
//        return productState;
//    }
//
//    public void setProductState(String productState) {
//        this.productState = productState;
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
//    public Integer getProductStar() {
//        return productStar;
//    }
//
//    public void setProductStar(Integer productStar) {
//        this.productStar = productStar;
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
//    public String getProductInfo() {
//        return productInfo;
//    }
//
//    public void setProductInfo(String productInfo) {
//        this.productInfo = productInfo;
//    }
//
//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }
//
//    public String getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(String sellerId) {
//        this.sellerId = sellerId;
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
//    public Integer getV() {
//        return v;
//    }
//
//    public void setV(Integer v) {
//        this.v = v;
//    }
//
//
//    public String getFileFolder() {
//        return fileFolder;
//    }
//
//    public void setFileFolder(String fileFolder) {
//        this.fileFolder = fileFolder;
//    }
}
