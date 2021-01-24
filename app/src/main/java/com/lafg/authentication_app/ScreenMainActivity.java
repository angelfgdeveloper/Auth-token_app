package com.lafg.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lafg.authentication_app.ui.main.ScreenMainFragment;

public class ScreenMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ScreenMainFragment.newInstance())
                    .commitNow();
        }
    }
}