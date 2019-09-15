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
}
