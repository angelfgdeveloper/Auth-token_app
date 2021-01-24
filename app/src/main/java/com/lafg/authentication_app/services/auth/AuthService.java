package com.lafg.authentication_app.services.auth;

import com.lafg.authentication_app.models.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthService {

    @GET("login/renew")
    Call<ResponseLogin> getDataUser();

}
