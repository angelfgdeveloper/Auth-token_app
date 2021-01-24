package com.lafg.authentication_app.data;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.lafg.authentication_app.LoadingUser;
import com.lafg.authentication_app.helpers.Environment;
import com.lafg.authentication_app.helpers.MyApp;
import com.lafg.authentication_app.helpers.SharedPreferencesManager;
import com.lafg.authentication_app.models.response.ResponseLogin;
import com.lafg.authentication_app.services.auth.AuthClient;
import com.lafg.authentication_app.services.auth.AuthService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private AuthService authService;
    private MutableLiveData<ResponseLogin> allDataUser;

    Repository() {
        AuthClient authClient = AuthClient.getInstance();
        authService = authClient.getAuthService();

        allDataUser = getAllDataUser();
    }

    public MutableLiveData<ResponseLogin> getAllDataUser() {
        if (allDataUser == null) {
            allDataUser = new MutableLiveData<>();
        }

        Call<ResponseLogin> call = authService.getDataUser();
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NotNull Call<ResponseLogin> call, @NotNull Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    allDataUser.setValue(response.body());
                } else {
                    Toast.makeText(MyApp.getContext(), "Algo ha salido mal, vuelva abrir la app", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseLogin> call, @NotNull Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });


        return allDataUser;
    }
}
