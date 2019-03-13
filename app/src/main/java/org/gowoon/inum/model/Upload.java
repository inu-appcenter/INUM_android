package org.gowoon.inum.model;

import android.widget.EditText;

public class Upload {
    public static Upload upload = new Upload();
    public static Upload getInstance(){return upload;}

    private String productName;
    private String productState;
    private String productPrice;
    private String productInfo;
    private String method;
    private String place;
    private String userfile;

    public String getProductName(){return productName;}

    public void setProductName(EditText productName){this.productName = String.valueOf(productName);}

    public String getProductState(){return productState;}

    public void setProductState(String productState){this.productState = productState;}

    public String getProductPrice(){return  productPrice;}

    public void setProductPrice(String productPrice){this.productPrice = productPrice;}

    public  String getProductInfo(){return productInfo;}

    public void setProductInfo(String productInfo){this.productInfo = productInfo;}

    public String getMethod(){return method;}

    public void setMethod(String method){this.method = method;}

    public String getPlace(){return place;}

    public void setPlace(String place){this.place = place;}

    public String getUserfile(){return userfile;}

    public void setUserfile(String userfile){this.userfile = userfile;}

}
