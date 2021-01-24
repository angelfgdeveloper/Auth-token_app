package com.lafg.authentication_app.services.auth;

import com.lafg.authentication_app.helpers.Environment;
import com.lafg.authentication_app.helpers.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SharedPreferencesManager.getStringValue(Environment.TOKEN);
        Request request = chain.request().newBuilder().addHeader("x-token", token).build();

        return chain.proceed(request);
    }

}
