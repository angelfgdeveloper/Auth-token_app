package com.lafg.authentication_app.services.api;

import com.lafg.authentication_app.helpers.Environment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientBasic {
    public static ApiClientBasic instance = null;
    private ApiServiceBasic apiServiceBasic;
    private Retrofit retrofit;

    public ApiClientBasic(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Environment.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServiceBasic = retrofit.create(ApiServiceBasic.class);
    }

    public static ApiClientBasic getInstance(){
        if (instance == null){
            instance = new ApiClientBasic();
        }
        return instance;
    }

    public ApiServiceBasic getApiServiceBasic(){
        return apiServiceBasic;
    }

}
