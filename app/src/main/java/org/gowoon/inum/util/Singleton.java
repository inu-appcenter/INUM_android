package org.gowoon.inum.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    public static final RetrofitService retrofit = new Retrofit.Builder()
            .baseUrl("http://117.16.231.66:7000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService.class);
    public Singleton() {
    }
}
