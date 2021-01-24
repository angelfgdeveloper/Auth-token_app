package com.lafg.authentication_app.services.api;

import com.lafg.authentication_app.models.request.RequestLogin;
import com.lafg.authentication_app.models.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServiceBasic {

    @POST("login/")
    Call<ResponseLogin> getLogin(@Body RequestLogin requestLogin);

}
