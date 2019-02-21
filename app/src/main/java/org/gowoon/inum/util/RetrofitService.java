package org.gowoon.inum.util;

import com.google.gson.JsonObject;

import org.gowoon.inum.model.LoginResult;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.model.SearchIdResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    //User
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult>
    login(@Field("id") String id, @Field("passwd") String passwd, @Field("FCM") String FCM);

    @FormUrlEncoded
    @POST("account/delete")
    Call<JsonObject>
    secession(@Field("id") String id, @Field("passwd") String passwd);

    //Setting
    @FormUrlEncoded
    @POST("stateChange/newPassword")
    public Call<JsonObject>
    forgot_pw(@Field("id") String id, @Field("name") String name);

    @FormUrlEncoded
    @POST("stateChange/changeTel")
    public Call<JsonObject>
    changeTel(@Field("id") String id, @Field("newTel") String tel);

    @FormUrlEncoded
    @POST("stateChange/changePasswd")
    public Call<JsonObject>
    changePasswd(@Field("id") String id, @Field("pastPasswd") String pastpw, @Field("newPasswd") String newpw);

    //Product
    @POST("PSelect/main")
    public Call <ArrayList<ArrayList<MainProductResult>>>
    main(@Header("x-access-token") String main_token);

    @FormUrlEncoded
    @POST("PSelect/searchId")
    public Call<ArrayList<SearchIdResult>>
    searchId( @Field("sellerId") String sellerid);

    //report _ moonhee,119
    @FormUrlEncoded
    @POST("report")
    public Call<JsonObject>
    moonhee(@Field("kind") String kind, @Field("senderId") String senderId, @Field("context") String context);

    @FormUrlEncoded
    @POST("report")
    public Call<JsonObject>
    report(@Field("kind") String kind, @Field("senderId") String senderId, @Field("productId") String productId);

}
