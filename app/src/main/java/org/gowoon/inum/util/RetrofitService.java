package org.gowoon.inum.util;

import com.google.gson.JsonObject;

import org.gowoon.inum.model.BannerItemResult;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.model.UserInfoVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    //User
    // 회원가입
    @FormUrlEncoded
    @POST("account/signUp")
    Call<JsonObject>
    signUp(@Field("id") String id, @Field("passwd") String passwd, @Field("tel") String tel, @Field("major") String major, @Field("name") String name);

    // 로그인
    @FormUrlEncoded
    @POST("account/signIn")
    Call<JsonObject>
    signIn(@Field("id") String id, @Field("passwd") String passwd, @Field("FCM") String FCM);

    //  임시 비밀번호 발급
    @FormUrlEncoded
    @PUT("account/tmpPasswd")
    Call<JsonObject>
    forgotPw(@Field("id") String id, @Field("name") String name);

    // User Info to Token
    @GET("account/myPage")
    Call<UserInfoVO>
    userInfo(@Header("x-access-token") String token);
    
    @FormUrlEncoded
    @POST("account/delete")
    Call<JsonObject>
    secession(@Field("id") String id, @Field("passwd") String passwd);

    //Info Setting
    @FormUrlEncoded
    @PUT("account/changeTel")
    Call<JsonObject>
    changeTel(@Header("x-access-token") String userToken, @Field("passwd") String password, @Field("tel") String tel);

    @FormUrlEncoded
    @POST("stateChange/changePasswd")
    Call<JsonObject>
    changePasswd(@Field("id") String id, @Field("pastPasswd") String pastpw, @Field("newPasswd") String newpw);

    // Main Activity
    // banner
    @POST("readBanner")
    Call<BannerItemResult>
    readBanner();

    // All Product Load
    @GET("product/mainList")
    Call <ArrayList<ArrayList<MainProductResult>>>
    main(@Header("x-access-token") String token);

    /*
        GET 방식, URL/product/search/{searchName} 호출.
        단, 주소값이 "URL/product/search?searchName={searchName]" 이 됨.
    */
    @GET("product/search")
    Call<ArrayList<MainProductResult>>
    searchName(@Header("x-access-token") String userToken, @Query("searchName") String searchName);

    // search in Category
    @GET("product/categorySearch")
    Call<ArrayList<MainProductResult>>
    searchInCategory(@Header("x-access-token") String userToken, @QueryMap HashMap<String, String> map);

    @GET("product/oneItem")
    Call<ProductOneItemResult>
    productOneItem(@Header("x-access-token") String userToken, @Query("productId") String productId);

    // load userItem
    @GET("product/userItem")
    Call<ArrayList<SearchIdResult>>
    searchId(@Header("x-access-token") String userToken, @Query("userId") String userId);

    // load Category Product List
    @GET("product/category")
    Call<ArrayList<MainProductResult>>
    category(@Header("x-access-token") String userToken, @Query("category") String category);

    //report
    @FormUrlEncoded
    @POST("report/")
    Call<JsonObject>
    report(@Header("x-access-token") String userToken, @Field("kind") String kind, @Field("context") String context);

    // Product
    //`upload
    @Multipart
    @POST ("product/upload")
    Call<JsonObject>
    productUpload(@Header("x-access-token") String userToken
            , @Part("userfile") ArrayList<MultipartBody.Part> imageList
            , @Part("productName") String name
            , @Part("productState") String state
            , @Part("productPrice") Integer price
            , @Part("category") String category
            , @Part("productInfo") String info
            , @Part("method") String method
            , @Part("place") String place
    );

    // sold out
    @FormUrlEncoded
    @PUT("product/selling")
    Call<JsonObject>
    productSoldOut(@Header("x-access-token") String userToken, @Field("productId") String productId);

    // delete
    @FormUrlEncoded
    @DELETE("product/")
    Call<JsonObject>
    productDelete(@Header("x-access-token") String userToken, @Field("productId") String productId
            , @Field("fileFolder") String fileFolder);
}
