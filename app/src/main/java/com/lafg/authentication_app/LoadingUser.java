package com.lafg.authentication_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lafg.authentication_app.data.MyViewModel;
import com.lafg.authentication_app.helpers.Environment;
import com.lafg.authentication_app.helpers.SharedPreferencesManager;
import com.lafg.authentication_app.models.response.ResponseLogin;
import com.lafg.authentication_app.models.response.Usuario;

public class LoadingUser extends AppCompatActivity {

    public static final String TAG = LoadingUser.class.getSimpleName();
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_user);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getAllDataUser().observe(this, responseLogin -> {
            String newToken = responseLogin.getToken();
            Log.d(TAG, newToken);
            credentialsUser(newToken);
        });

    }

    public void credentialsUser(String token) {
        SharedPreferencesManager.setStringValue(Environment.TOKEN, token);

        Thread t = new Thread(() -> {
            String isToken = SharedPreferencesManager.getStringValue(Environment.TOKEN);

            if (!isToken.trim().isEmpty()) {
                Intent i = new Intent(LoadingUser.this, ScreenMainActivity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(LoadingUser.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        t.start();
    }

}
