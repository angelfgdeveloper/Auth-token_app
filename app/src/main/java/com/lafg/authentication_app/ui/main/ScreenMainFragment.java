package com.lafg.authentication_app.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lafg.authentication_app.LoadingUser;
import com.lafg.authentication_app.LoginActivity;
import com.lafg.authentication_app.R;
import com.lafg.authentication_app.ScreenMainActivity;
import com.lafg.authentication_app.data.MyViewModel;
import com.lafg.authentication_app.helpers.Environment;
import com.lafg.authentication_app.helpers.SharedPreferencesManager;
import com.lafg.authentication_app.models.response.ResponseLogin;
import com.lafg.authentication_app.models.response.Usuario;

public class ScreenMainFragment extends Fragment {

    public static final String TAG = LoadingUser.class.getSimpleName();
    private TextView tvTitle;
    private Button btnSignOff;

    private MyViewModel myViewModel;
    private ResponseLogin mResponseLogin;
    private Usuario usuario;

    public static ScreenMainFragment newInstance() {
        return new ScreenMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        tvTitle = v.findViewById(R.id.textViewTitle);
        btnSignOff = v.findViewById(R.id.buttonSignOff);
        mResponseLogin = new ResponseLogin();
        usuario = new Usuario();

        btnSignOff.setOnClickListener(v1 -> goToLogin());

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getAllDataUser().observe(getViewLifecycleOwner(), responseLogin -> {
            mResponseLogin.setOk(responseLogin.isOk());
            mResponseLogin.setToken(responseLogin.getToken());
            usuario.setOnline(responseLogin.getUsuario().isOnline());
            usuario.setNombre(responseLogin.getUsuario().getNombre());
            usuario.setEmail(responseLogin.getUsuario().getEmail());
            usuario.setUid(responseLogin.getUsuario().getUid());
            mResponseLogin.setUsuario(usuario);

            tvTitle.setText(usuario.getNombre());
        });

    }

    private void goToLogin() {
        SharedPreferencesManager.removeDataValue(Environment.TOKEN);
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

}