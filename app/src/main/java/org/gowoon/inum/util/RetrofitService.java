package org.gowoon.inum.util;

import org.gowoon.inum.model.retrofit_login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("tlogin")
    public Call<retrofit_login>
    login(@Field("id") String id, @Field("passwd") String passwd, @Field("FCM") String FCM);
}
