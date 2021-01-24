package com.lafg.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lafg.authentication_app.helpers.Environment;
import com.lafg.authentication_app.helpers.SharedPreferencesManager;
import com.lafg.authentication_app.models.request.RequestLogin;
import com.lafg.authentication_app.models.response.ResponseLogin;
import com.lafg.authentication_app.services.api.ApiClientBasic;
import com.lafg.authentication_app.services.api.ApiServiceBasic;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etEmail, etPassword;
    private Button btnSignIn;

    private ApiClientBasic apiClient;
    private ApiServiceBasic apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnSignIn = findViewById(R.id.buttonSignIn);

        init();
        
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid();
            }
        });
    }

    private void init() {
        apiClient = ApiClientBasic.getInstance();
        apiService = apiClient.getApiServiceBasic();
    }

    private void valid() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Iniciando sesión..", Toast.LENGTH_SHORT).show();
            signIn(email, password);
        } else {
            etEmail.setError("Crendeciales ivalidas");
            etPassword.setError("Crendeciales ivalidas");
        }

    }

    private void signIn(String email, String password) {
        RequestLogin requestLogin = new RequestLogin(email, password);

        Call<ResponseLogin> call = apiService.getLogin(requestLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NotNull Call<ResponseLogin> call, @NotNull Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getToken();
                    SharedPreferencesManager.setStringValue(Environment.TOKEN, token);
                    goToMain();
                } else {
                    Toast.makeText(LoginActivity.this, "Algo a salido mal, vuelva a intentar..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseLogin> call, @NotNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void goToMain() {
        Intent i = new Intent(LoginActivity.this, ScreenMainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String token = SharedPreferencesManager.getStringValue(Environment.TOKEN);

        if (!token.trim().isEmpty()) {
            goToMain();
        }
    }
}