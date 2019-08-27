package org.gowoon.inum.model;

import android.widget.EditText;

import lombok.Data;

@Data
public class UploadDTO {
    public static UploadDTO UploadDTO = new UploadDTO();
    public static UploadDTO getInstance(){return UploadDTO;}

    private String productName;
    private String productState;
    private String productPrice;
    private String productInfo;
    private String method;
    private String place;
    private String userfile;
    private String category;

//    public String getProductName(String productName){return this.productName;}
//
//    public void setProductName(String productName){this.productName = String.valueOf(productName);}
//
//    public String getProductState(String productState){return this.productState;}
//
//    public void setProductState(String productState){this.productState = productState;}
//
//    public String getProductPrice(String productPrice){return this.productPrice;}
//
//    public void setProductPrice(String productPrice){this.productPrice = productPrice;}
//
//    public  String getProductInfo(String productInfo){return this.productInfo;}
//
//    public void setProductInfo(String productInfo){this.productInfo = productInfo;}
//
//    public String getMethod(String method){return this.method;}
//
//    public void setMethod(String method){this.method = method;}
//
//    public String getPlace(String place){return this.place;}
//
//    public void setPlace(String place){this.place = place;}
//
//    public String getUserfile(){return userfile;}
//
//    public void setUserfile(String userfile){this.userfile = userfile;}
//
//    public String getProductCategory(String category){return this.category;}
//
//    public void setProductCategory(String category){this.category = category;}

}
