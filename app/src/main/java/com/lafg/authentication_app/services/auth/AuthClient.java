package com.lafg.authentication_app.services.auth;

import com.lafg.authentication_app.helpers.Environment;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthClient {

    public static AuthClient instance = null;
    private AuthService authService;
    private Retrofit retrofit;

    public AuthClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient client = okHttpClientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Environment.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        authService = retrofit.create(AuthService.class);
    }

    public static AuthClient getInstance() {
        if (instance == null) {
            instance = new AuthClient();
        }
        return instance;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
