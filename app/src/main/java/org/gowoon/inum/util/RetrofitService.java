package org.gowoon.inum.util;

import org.gowoon.inum.model.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult>
    login(@Field("id") String id, @Field("passwd") String passwd, @Field("FCM") String FCM);
}
