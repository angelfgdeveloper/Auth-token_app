package com.lafg.authentication_app.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lafg.authentication_app.models.response.ResponseLogin;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<ResponseLogin> allDataUser;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        allDataUser = repository.getAllDataUser();
    }

    public LiveData<ResponseLogin> getAllDataUser() {
        return allDataUser;
    }

}
